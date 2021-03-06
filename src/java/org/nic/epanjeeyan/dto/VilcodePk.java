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
 * This class represents the primary key of the vilcode table.
 */
public class VilcodePk implements Serializable
{
	protected String vlcode;

	/** 
	 * Sets the value of vlcode
	 */
	public void setVlcode(String vlcode)
	{
		this.vlcode = vlcode;
	}

	/** 
	 * Gets the value of vlcode
	 */
	public String getVlcode()
	{
		return vlcode;
	}

	/**
	 * Method 'VilcodePk'
	 * 
	 */
	public VilcodePk()
	{
	}

	/**
	 * Method 'VilcodePk'
	 * 
	 * @param vlcode
	 */
	public VilcodePk(final String vlcode)
	{
		this.vlcode = vlcode;
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
		
		if (!(_other instanceof VilcodePk)) {
			return false;
		}
		
		final VilcodePk _cast = (VilcodePk) _other;
		if (vlcode == null ? _cast.vlcode != vlcode : !vlcode.equals( _cast.vlcode )) {
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
		if (vlcode != null) {
			_hashCode = 29 * _hashCode + vlcode.hashCode();
		}
		
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
		ret.append( "org.nic.epanjeeyan.dto.VilcodePk: " );
		ret.append( "vlcode=" + vlcode );
		return ret.toString();
	}

}
