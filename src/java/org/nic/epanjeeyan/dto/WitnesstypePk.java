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
 * This class represents the primary key of the witnesstype table.
 */
public class WitnesstypePk implements Serializable
{
	protected int slno;

	/** 
	 * This attribute represents whether the primitive attribute slno is null.
	 */
	protected boolean slnoNull;

	/** 
	 * Sets the value of slno
	 */
	public void setSlno(int slno)
	{
		this.slno = slno;
	}

	/** 
	 * Gets the value of slno
	 */
	public int getSlno()
	{
		return slno;
	}

	/**
	 * Method 'WitnesstypePk'
	 * 
	 */
	public WitnesstypePk()
	{
	}

	/**
	 * Method 'WitnesstypePk'
	 * 
	 * @param slno
	 */
	public WitnesstypePk(final int slno)
	{
		this.slno = slno;
	}

	/** 
	 * Sets the value of slnoNull
	 */
	public void setSlnoNull(boolean slnoNull)
	{
		this.slnoNull = slnoNull;
	}

	/** 
	 * Gets the value of slnoNull
	 */
	public boolean isSlnoNull()
	{
		return slnoNull;
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
		
		if (!(_other instanceof WitnesstypePk)) {
			return false;
		}
		
		final WitnesstypePk _cast = (WitnesstypePk) _other;
		if (slno != _cast.slno) {
			return false;
		}
		
		if (slnoNull != _cast.slnoNull) {
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
		_hashCode = 29 * _hashCode + slno;
		_hashCode = 29 * _hashCode + (slnoNull ? 1 : 0);
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
		ret.append( "org.nic.epanjeeyan.dto.WitnesstypePk: " );
		ret.append( "slno=" + slno );
		return ret.toString();
	}

}
