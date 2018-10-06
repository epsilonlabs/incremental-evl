package org.eclipse.epsilon.emc.csv.incremental;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.epsilon.base.incremental.execute.IIncrementalModule;
import org.eclipse.epsilon.emc.csv.CsvModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class for comparing CsvModels. This assumes that elements in the models
 * have comparable ids and that those ids are final.
 * 
 * @author Horacio Hoyos
 *
 */
public class CsvCompare {

	private static final Logger logger = LoggerFactory.getLogger(CsvCompare.class);

	public class CsvComparison {

		Set<CsvMatch> matches;
		Set<Diff> diff;
		final boolean knownHeaders;

		public CsvComparison(boolean knownHeaders) {
			this.knownHeaders = knownHeaders;
		}

		public Set<CsvMatch> getMatches() {
			if (matches == null) {
				matches = new HashSet<>();
			}
			return matches;
		}

		public Set<Diff> getDifferences() {
			if (diff == null) {
				diff = new HashSet<>();
			}
			return diff;
		}

		public boolean isKnownHeaders() {
			return knownHeaders;
		}

	}

	public class CsvMatch {

		Map<String, Object> left;
		Map<String, Object> right;
		final CsvComparison comparison;

		public CsvMatch(CsvComparison comparison) {
			super();
			this.comparison = comparison;
		}

		public Map<String, Object> getLeft() {
			return left;
		}

		public void setLeft(Map<String, Object> left) {
			this.left = left;
		}

		public Map<String, Object> getRight() {
			return right;
		}

		public void setRight(Map<String, Object> right) {
			this.right = right;
		}

		public CsvComparison getComparison() {
			return comparison;
		}

	}

	public enum DifferenceKind {
		ADD, DELETE, CHANGE,
	}

	public enum DifferenceSource {
		LEFT, RIGHT
	}

	public class Diff {

		private final DifferenceKind KIND_EDEFAULT = DifferenceKind.ADD;

		private final DifferenceSource SOURCE_EDEFAULT = DifferenceSource.LEFT;

		protected DifferenceKind kind = KIND_EDEFAULT;
		protected DifferenceSource source = SOURCE_EDEFAULT;

		final CsvMatch match;
		String fieldName;

		public Diff(CsvMatch match) {
			super();
			this.match = match;
		}

		public DifferenceKind getKind() {
			return kind;
		}

		public void setKind(DifferenceKind kind) {
			this.kind = kind;
		}

		public DifferenceSource getSource() {
			return source;
		}

		public void setSource(DifferenceSource source) {
			this.source = source;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public CsvMatch getMatch() {
			return match;
		}

	}

	/**
	 * This is the entry point of a Comparison process.
	 * <p>
	 * The returned Comparison should include both matched an unmatched objects. It
	 * is not the match engine's responsibility to determine differences between
	 * objects, only to match them together.
	 * </p>
	 * 
	 * @param leftModel
	 * @param rightModel
	 * @return An initialized {@link Comparison} model with all matches determined.
	 */
	public CsvComparison match(CsvModel leftModel, CsvModel rightModel) {

		if (leftModel.isKnownHeaders() != rightModel.isKnownHeaders()) {
			throw new IllegalArgumentException("Both CSV models must use headers or not");
		}
		logger.info("findMatches");
		CsvComparison comparison = new CsvComparison(leftModel.isKnownHeaders());
		List<Map<String, Object>> leftRows = new LinkedList<>(leftModel.allContents());
		List<Map<String, Object>> rightRows = new LinkedList<>(rightModel.allContents());
		logger.debug("Sorting model rows");
		Collections.sort(leftRows, getRowComparator(leftModel));
		Collections.sort(rightRows, getRowComparator(rightModel));
		Comparator<Map<String, Object>> rowComparator = getRowComparator(leftModel, rightModel);
		Iterator<Map<String, Object>> leftIterator = leftRows.iterator();
		Iterator<Map<String, Object>> rightIterator = rightRows.iterator();
		logger.debug("leftRows size = " + leftRows.size());
		logger.debug("rightRows size = " + rightRows.size());
		int cmpRslt = 0;
		Map<String, Object> leftRow = null;
		Map<String, Object> rightRow = null;
		while (leftIterator.hasNext() && rightIterator.hasNext()) {
			// Use the compare result to advance the iterators. If 0 advance both, if <0
			// advance left until we match,
			// if >0 advance right till we match
			if (cmpRslt == 0) {
				leftRow = leftIterator.next();
				rightRow = rightIterator.next();
			} else if (cmpRslt < 0) {
				leftRow = leftIterator.next();
			} else {
				rightRow = rightIterator.next();
			}
			logger.debug("comparing {} to {}", leftRow, rightRow);
			cmpRslt = rowComparator.compare(leftRow, rightRow);
			logger.debug("Compare result was: {}", cmpRslt);
			CsvMatch match = new CsvMatch(comparison);
			if (cmpRslt == 0) {
				logger.info("Rows match");
				match.setLeft(leftRow);
				match.setRight(rightRow);
			} else if (cmpRslt < 0) {
				logger.info("{} not in rightModel", leftRow);
				// The leftRow row is not in the rightModel
				match.setLeft(leftRow);
				comparison.getMatches().add(match);
			} else {
				logger.info("{} not in leftModel", rightRow);
				// The right row is not in the leftModel
				match.setRight(rightRow);
			}
			comparison.getMatches().add(match);
		}
		// Deal with different number of rows
		while (rightIterator.hasNext()) {
			rightRow = rightIterator.next();
			logger.info("Additional row in rightModel: {}", rightRow);
			CsvMatch match = new CsvMatch(comparison);
			match.setRight(rightRow);
			comparison.getMatches().add(match);

		}
		while (leftIterator.hasNext()) {
			logger.info("Additional row in leftModel: {}", leftRow);
			leftRow = leftIterator.next();
			CsvMatch match = new CsvMatch(comparison);
			match.setLeft(leftRow);
			comparison.getMatches().add(match);
		}
		return comparison;
	}

	public void diff(CsvComparison comparison) {

		for (CsvMatch m : comparison.getMatches()) {
			if (m.getLeft() == null) {
				Diff d = new Diff(m);
				d.setSource(DifferenceSource.RIGHT);
				d.setKind(DifferenceKind.ADD);
				comparison.getDifferences().add(d);
			} else if (m.getRight() == null) {
				Diff d = new Diff(m);
				d.setSource(DifferenceSource.LEFT);
				d.setKind(DifferenceKind.DELETE);
				comparison.getDifferences().add(d);
			} else {
				if (comparison.knownHeaders) {
					for (String k : m.getLeft().keySet()) {
						Object leftValue = m.getLeft().get(k);
						Object rightValue = m.getRight().get(k);
						logger.debug("comparing field {}: {} vs. {}", k, leftValue, rightValue);
						if (!leftValue.equals(rightValue)) {
							logger.info("Change detected");
							Diff d = new Diff(m);
							d.setKind(DifferenceKind.CHANGE);
							d.setFieldName(k);
							d.setSource(DifferenceSource.RIGHT);
							comparison.getDifferences().add(d);
						}
					}
				} else {
					@SuppressWarnings("unchecked")
					List<String> oldValue = (List<String>) m.getLeft().get(CsvModel.HEADERLESS_FIELD_NAME);
					@SuppressWarnings("unchecked")
					List<String> newValue = (List<String>) m.getRight().get(CsvModel.HEADERLESS_FIELD_NAME);
					logger.debug("comparing all fields {} vs. {}", oldValue, newValue);
					if (!oldValue.equals(newValue)) {
						logger.info("Change detected");
						Diff d = new Diff(m);
						d.setKind(DifferenceKind.CHANGE);
						d.setFieldName(CsvModel.HEADERLESS_FIELD_NAME);
						d.setSource(DifferenceSource.RIGHT);
						comparison.getDifferences().add(d);
					}
				}
			}
		}
	}

	/**
	 * Create a comparator that compares rows of the same model using the idFiled
	 * (either name or position)
	 */
	private Comparator<Map<String, Object>> getRowComparator(final CsvModel model) {
		return new Comparator<Map<String, Object>>() {

			@SuppressWarnings("unchecked")
			@Override
			public int compare(Map<String, Object> row1, Map<String, Object> row2) {
				String id1;
				String id2;
				if (model.isKnownHeaders()) {
					id1 = (String) row1.get(model.getIdFieldName());
					id2 = (String) row2.get(model.getIdFieldName());
				} else {
					id1 = (String) ((List<String>) row1.get("field")).get(model.getIdFieldIndex());
					id2 = (String) ((List<String>) row2.get("field")).get(model.getIdFieldIndex());
				}
				return id1.compareTo(id2);
			}
		};

	}

	/**
	 * Create a comparator that compares rows of the different models using the
	 * idFiled (either name or position)
	 */
	private Comparator<Map<String, Object>> getRowComparator(final CsvModel left, final CsvModel right) {
		return new Comparator<Map<String, Object>>() {

			@SuppressWarnings("unchecked")
			@Override
			public int compare(Map<String, Object> leftRow, Map<String, Object> rightRow) {
				String leftId;
				String rightId;
				if (left.isKnownHeaders()) {
					leftId = (String) leftRow.get(left.getIdFieldName());
				} else {
					leftId = (String) ((List<String>) leftRow.get("field")).get(left.getIdFieldIndex());
				}
				if (right.isKnownHeaders()) {
					rightId = (String) rightRow.get(right.getIdFieldName());
				} else {
					rightId = (String) ((List<String>) rightRow.get("field")).get(right.getIdFieldIndex());
				}
				return leftId.compareTo(rightId);
			}
		};

	}

}
