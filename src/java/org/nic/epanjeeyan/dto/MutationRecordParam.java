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

public class MutationRecordParam implements Serializable
{
	protected java.util.Date from_casedate;

	protected java.util.Date to_casedate;

	/**
	 * Method 'MutationRecordParam'
	 * 
	 */
	public MutationRecordParam()
	{
	}

	/**
	 * Method 'getFrom_casedate'
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getFrom_casedate()
	{
		return from_casedate;
	}

	/**
	 * Method 'setFrom_casedate'
	 * 
	 * @param from_casedate
	 */
	public void setFrom_casedate(java.util.Date from_casedate)
	{
		this.from_casedate = from_casedate;
	}

	/**
	 * Method 'getTo_casedate'
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getTo_casedate()
	{
		return to_casedate;
	}

	/**
	 * Method 'setTo_casedate'
	 * 
	 * @param to_casedate
	 */
	public void setTo_casedate(java.util.Date to_casedate)
	{
		this.to_casedate = to_casedate;
	}

}
