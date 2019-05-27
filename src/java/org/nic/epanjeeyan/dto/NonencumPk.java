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
 * This class represents the primary key of the nonencum table.
 */
public class NonencumPk implements Serializable
{
	protected int srlno;

	protected String applno;

	/** 
	 * This attribute represents whether the primitive attribute srlno is null.
	 */
	protected boolean srlnoNull;

	/** 
	 * Sets the value of srlno
	 */
	public void setSrlno(int srlno)
	{
		this.srlno = srlno;
	}

	/** 
	 * Gets the value of srlno
	 */
	public int getSrlno()
	{
		return srlno;
	}

	/** 
	 * Sets the value of applno
	 */
	public void setApplno(String applno)
	{
		this.applno = applno;
	}

	/** 
	 * Gets the value of applno
	 */
	public String getApplno()
	{
		return applno;
	}

	/**
	 * Method 'NonencumPk'
	 * 
	 */
	public NonencumPk()
	{
	}

	/**
	 * Method 'NonencumPk'
	 * 
	 * @param srlno
	 * @param applno
	 */
	public NonencumPk(final int srlno, final String applno)
	{
		this.srlno = srlno;
		this.applno = applno;
	}

	/** 
	 * Sets the value of srlnoNull
	 */
	public void setSrlnoNull(boolean srlnoNull)
	{
		this.srlnoNull = srlnoNull;
	}

	/** 
	 * Gets the value of srlnoNull
	 */
	public boolean isSrlnoNull()
	{
		return srlnoNull;
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
		
		if (!(_other instanceof NonencumPk)) {
			return false;
		}
		
		final NonencumPk _cast = (NonencumPk) _other;
		if (srlno != _cast.srlno) {
			return false;
		}
		
		if (applno == null ? _cast.applno != applno : !applno.equals( _cast.applno )) {
			return false;
		}
		
		if (srlnoNull != _cast.srlnoNull) {
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
		_hashCode = 29 * _hashCode + srlno;
		if (applno != null) {
			_hashCode = 29 * _hashCode + applno.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (srlnoNull ? 1 : 0);
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
		ret.append( "org.nic.epanjeeyan.dto.NonencumPk: " );
		ret.append( "srlno=" + srlno );
		ret.append( ", applno=" + applno );
		return ret.toString();
	}

}