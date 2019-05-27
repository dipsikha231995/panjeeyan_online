/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package org.nic.epanjeeyan.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the category table.
 */
public class CategoryPk implements Serializable
{
	protected int code;

	protected int sdcode;

	/** 
	 * This attribute represents whether the primitive attribute code is null.
	 */
	protected boolean codeNull;

	/** 
	 * This attribute represents whether the primitive attribute sdcode is null.
	 */
	protected boolean sdcodeNull;

	/** 
	 * Sets the value of code
	 */
	public void setCode(int code)
	{
		this.code = code;
	}

	/** 
	 * Gets the value of code
	 */
	public int getCode()
	{
		return code;
	}

	/** 
	 * Sets the value of sdcode
	 */
	public void setSdcode(int sdcode)
	{
		this.sdcode = sdcode;
	}

	/** 
	 * Gets the value of sdcode
	 */
	public int getSdcode()
	{
		return sdcode;
	}

	/**
	 * Method 'CategoryPk'
	 * 
	 */
	public CategoryPk()
	{
	}

	/**
	 * Method 'CategoryPk'
	 * 
	 * @param code
	 * @param sdcode
	 */
	public CategoryPk(final int code, final int sdcode)
	{
		this.code = code;
		this.sdcode = sdcode;
	}

	/** 
	 * Sets the value of codeNull
	 */
	public void setCodeNull(boolean codeNull)
	{
		this.codeNull = codeNull;
	}

	/** 
	 * Gets the value of codeNull
	 */
	public boolean isCodeNull()
	{
		return codeNull;
	}

	/** 
	 * Sets the value of sdcodeNull
	 */
	public void setSdcodeNull(boolean sdcodeNull)
	{
		this.sdcodeNull = sdcodeNull;
	}

	/** 
	 * Gets the value of sdcodeNull
	 */
	public boolean isSdcodeNull()
	{
		return sdcodeNull;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof CategoryPk)) {
			return false;
		}
		
		final CategoryPk _cast = (CategoryPk) _other;
		if (code != _cast.code) {
			return false;
		}
		
		if (sdcode != _cast.sdcode) {
			return false;
		}
		
		if (codeNull != _cast.codeNull) {
			return false;
		}
		
		if (sdcodeNull != _cast.sdcodeNull) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		_hashCode = 29 * _hashCode + code;
		_hashCode = 29 * _hashCode + sdcode;
		_hashCode = 29 * _hashCode + (codeNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (sdcodeNull ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "org.nic.epanjeeyan.dto.CategoryPk: " );
		ret.append( "code=" + code );
		ret.append( ", sdcode=" + sdcode );
		return ret.toString();
	}

}
