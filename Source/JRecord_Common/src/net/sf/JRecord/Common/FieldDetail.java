/*
 * Created on 7/05/2004
 *
 * This class stores the description of one field in a record.
 */
package net.sf.JRecord.Common;

//import net.sf.JRecord.Details.RecordDetail;


/**
 * This class stores the description of one field in a record (or Line).
 * It is used by the {@link RecordDetail} class
 *
 * <pre>
 *     LayoutDetail  - Describes a file
 *       |
 *       +----- RecordDetail (1 or More) - Describes one record in the file
 *                |
 *                +------  FieldDetail (1 or More)  - Describes one field in the file
 * </pre>
 *
 *
 * @author Bruce Martin
 * @version 0.55
 *
 */
public class FieldDetail implements IFieldDetail {
	private static final AbstractRecord DEFAULT_RECORD = new AbstractRecord() {

		/**
		 * @see net.sf.JRecord.Common.AbstractRecord#getParentRecordIndex()
		 */
		public int getParentRecordIndex() {
			return 0;
		}

		/**
		 * @see net.sf.JRecord.Common.AbstractRecord#getQuote()
		 */
		public String getQuote() {
			return null;
		}

		/**
		 * @see net.sf.JRecord.Common.AbstractRecord#getRecordStyle()
		 */
		public int getRecordStyle() {
			return 0;
		}

		public int getSourceIndex() {
			return 0;
		}

	};
	private int pos;
	private int len;
	private int end;
	private String name;
	private final String description;
	private int type;
	private final int decimal;
	private final String fontName;
	private final int format;
	private final String paramater;
	//private String quote;
	private AbstractRecord record = DEFAULT_RECORD;
	private Object defaultValue = null;
	private String groupName = "";


	/**
	 * Create a field definition
	 *
	 * @param pName field name
	 * @param pDescription field description
	 * @param pType field type
	 * @param pDecimal number of decimal places
	 * @param pFont fontname
	 * @param pFormat screen format id
	 * @param pParamater Field paramater
	 */
	public FieldDetail(final String pName,
	        		   final String pDescription,
	        		   final int pType,
	        		   final int pDecimal,
	        		   final String pFont,
					   final int pFormat,
					   final String pParamater) {

		name        = pName;
		type        = pType;
		decimal     = pDecimal;
		fontName    = pFont;
		format      = pFormat;
		paramater   = pParamater;

		if (pDescription == null) {
		    description = "";
		} else {
		    description = pDescription;
		}
	}


	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#setPosLen(int, int)
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#setPosLen(int, int)
	 */
	@Override
	public final IFieldDetail setPosLen(final int pPosition, final int pLength) {
		pos = pPosition;
		len = pLength;
		end = pos + len - 1;

		return this;
	}


	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#setPosOnly(int)
	 */
	@Override
	public FieldDetail setPosOnly(final int pPosition) {
		pos = pPosition;
		len = Constants.NULL_INTEGER;
		end = Constants.NULL_INTEGER;

		return this;
	}



	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#getDecimal()
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#getDecimal()
	 */
	@Override
	public final int getDecimal() {
		return decimal;
	}


	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#getLen()
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#getLen()
	 */
	@Override
	public int getLen() {
		return len;
	}


	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#getName()
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#getName()
	 */
	@Override
	public String getName() {
		return name;
	}


	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#getPos()
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#getPos()
	 */
	@Override
	public int getPos() {
		return pos;
	}



	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#getType()
	 */
	@Override
	public int getType() {
		return type;
	}


	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}


	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#getEnd()
	 */
	@Override
	public int getEnd() {
		return end;
	}


	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#isFixedFormat()
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#isFixedFormat()
	 */
	@Override
	public boolean isFixedFormat() {
		return end != Constants.NULL_INTEGER;
	}


	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#getFontName()
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#getFontName()
	 */
	@Override
	public String getFontName() {
        return fontName;
    }

    /* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#getFormat()
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#getFormat()
	 */
	@Override
	public int getFormat() {
		return format;
	}

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#getParamater()
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#getParamater()
	 */
	@Override
	public String getParamater() {
		return paramater;
	}

    /* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#getQuote()
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#getQuote()
	 */
	@Override
	public String getQuote() {
        return record.getQuote();
    }


	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#getRecord()
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#getRecord()
	 */
	@Override
	public AbstractRecord getRecord() {
		return record;
	}


	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#setRecord(net.sf.JRecord.Common.AbstractRecord)
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#setRecord(net.sf.JRecord.Common.AbstractRecord)
	 */
	@Override
	public void setRecord(AbstractRecord record) {
		this.record = record;
	}

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#setNameType(java.lang.String, int)
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#setNameType(java.lang.String, int)
	 */
	@Override
	public void setNameType(String newName, int newType) {
		this.name = newName;
		this.type = newType;
	}


	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#getDefaultValue()
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#getDefaultValue()
	 */
	@Override
	public Object getDefaultValue() {
		return defaultValue;
	}


	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.AbstractFieldDetails#setDefaultValue(java.lang.Object)
	 */

	/* (non-Javadoc)
	 * @see net.sf.JRecord.Common.IFieldDetail#setDefaultValue(java.lang.Object)
	 */
	@Override
	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}


	/**
	 * @return the groupName
	 */
	public final String getGroupName() {
		return groupName;
	}


	/**
	 * @param groupName the groupName to set
	 */
	public final void setGroupName(String groupName) {
		this.groupName = groupName;
	}
 }
