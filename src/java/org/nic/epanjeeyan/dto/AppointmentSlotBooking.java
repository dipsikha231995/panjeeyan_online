/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package org.nic.epanjeeyan.dto;

import org.nic.epanjeeyan.dao.*;
import org.nic.epanjeeyan.factory.*;
import org.nic.epanjeeyan.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class AppointmentSlotBooking implements Serializable
{
	/** 
	 * This attribute maps to the column slno in the appointment_slot_booking table.
	 */
	protected int slno;

	/** 
	 * This attribute represents whether the primitive attribute slno is null.
	 */
	protected boolean slnoNull = true;

	/** 
	 * This attribute maps to the column appointment_id in the appointment_slot_booking table.
	 */
	protected String appointmentId;

	/** 
	 * This attribute maps to the column officer_id in the appointment_slot_booking table.
	 */
	protected int officerId;

	/** 
	 * This attribute maps to the column applicant_name in the appointment_slot_booking table.
	 */
	protected String applicantName;

	/** 
	 * This attribute maps to the column email in the appointment_slot_booking table.
	 */
	protected String email;

	/** 
	 * This attribute maps to the column mobile_number in the appointment_slot_booking table.
	 */
	protected String mobileNumber;

	/** 
	 * This attribute maps to the column applicant_address in the appointment_slot_booking table.
	 */
	protected String applicantAddress;

	/** 
	 * This attribute maps to the column applicant_type in the appointment_slot_booking table.
	 */
	protected String applicantType;

	/** 
	 * This attribute maps to the column area_type in the appointment_slot_booking table.
	 */
	protected String areaType;

	/** 
	 * This attribute maps to the column application_date in the appointment_slot_booking table.
	 */
	protected Date applicationDate;

	/** 
	 * This attribute maps to the column application_date_time in the appointment_slot_booking table.
	 */
	protected Date applicationDateTime;

	/** 
	 * This attribute maps to the column deed_type in the appointment_slot_booking table.
	 */
	protected int deedType;

	/** 
	 * This attribute represents whether the primitive attribute deedType is null.
	 */
	protected boolean deedTypeNull = true;

	/** 
	 * This attribute maps to the column deed_subtype in the appointment_slot_booking table.
	 */
	protected String deedSubtype;

	/** 
	 * This attribute maps to the column consideration_amount in the appointment_slot_booking table.
	 */
	protected int considerationAmount;

	/** 
	 * This attribute represents whether the primitive attribute considerationAmount is null.
	 */
	protected boolean considerationAmountNull = true;

	/** 
	 * This attribute maps to the column registration_fee in the appointment_slot_booking table.
	 */
	protected int registrationFee;

	/** 
	 * This attribute represents whether the primitive attribute registrationFee is null.
	 */
	protected boolean registrationFeeNull = true;

	/** 
	 * This attribute maps to the column stamp_duty in the appointment_slot_booking table.
	 */
	protected int stampDuty;

	/** 
	 * This attribute represents whether the primitive attribute stampDuty is null.
	 */
	protected boolean stampDutyNull = true;

	/** 
	 * This attribute maps to the column document_to_be_furnished in the appointment_slot_booking table.
	 */
	protected String documentToBeFurnished;

	/** 
	 * This attribute maps to the column doc_subject in the appointment_slot_booking table.
	 */
	protected String docSubject;

	/** 
	 * This attribute maps to the column whether_land in the appointment_slot_booking table.
	 */
	protected short whetherLand;

	/** 
	 * This attribute maps to the column gender in the appointment_slot_booking table.
	 */
	protected String gender;

	/** 
	 * This attribute maps to the column act in the appointment_slot_booking table.
	 */
	protected String act;

	/** 
	 * This attribute maps to the column sro_office in the appointment_slot_booking table.
	 */
	protected int sroOffice;

	/** 
	 * This attribute maps to the column appointment_date in the appointment_slot_booking table.
	 */
	protected Date appointmentDate;

	/** 
	 * This attribute maps to the column appointment_time_slot in the appointment_slot_booking table.
	 */
	protected Date appointmentTimeSlot;

	/**
	 * Method 'AppointmentSlotBooking'
	 * 
	 */
	public AppointmentSlotBooking()
	{
	}

	/**
	 * Method 'getSlno'
	 * 
	 * @return int
	 */
	public int getSlno()
	{
		return slno;
	}

	/**
	 * Method 'setSlno'
	 * 
	 * @param slno
	 */
	public void setSlno(int slno)
	{
		this.slno = slno;
		this.slnoNull = false;
	}

	/**
	 * Method 'setSlnoNull'
	 * 
	 * @param value
	 */
	public void setSlnoNull(boolean value)
	{
		this.slnoNull = value;
	}

	/**
	 * Method 'isSlnoNull'
	 * 
	 * @return boolean
	 */
	public boolean isSlnoNull()
	{
		return slnoNull;
	}

	/**
	 * Method 'getAppointmentId'
	 * 
	 * @return String
	 */
	public String getAppointmentId()
	{
		return appointmentId;
	}

	/**
	 * Method 'setAppointmentId'
	 * 
	 * @param appointmentId
	 */
	public void setAppointmentId(String appointmentId)
	{
		this.appointmentId = appointmentId;
	}

	/**
	 * Method 'getOfficerId'
	 * 
	 * @return int
	 */
	public int getOfficerId()
	{
		return officerId;
	}

	/**
	 * Method 'setOfficerId'
	 * 
	 * @param officerId
	 */
	public void setOfficerId(int officerId)
	{
		this.officerId = officerId;
	}

	/**
	 * Method 'getApplicantName'
	 * 
	 * @return String
	 */
	public String getApplicantName()
	{
		return applicantName;
	}

	/**
	 * Method 'setApplicantName'
	 * 
	 * @param applicantName
	 */
	public void setApplicantName(String applicantName)
	{
		this.applicantName = applicantName;
	}

	/**
	 * Method 'getEmail'
	 * 
	 * @return String
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Method 'setEmail'
	 * 
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Method 'getMobileNumber'
	 * 
	 * @return String
	 */
	public String getMobileNumber()
	{
		return mobileNumber;
	}

	/**
	 * Method 'setMobileNumber'
	 * 
	 * @param mobileNumber
	 */
	public void setMobileNumber(String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}

	/**
	 * Method 'getApplicantAddress'
	 * 
	 * @return String
	 */
	public String getApplicantAddress()
	{
		return applicantAddress;
	}

	/**
	 * Method 'setApplicantAddress'
	 * 
	 * @param applicantAddress
	 */
	public void setApplicantAddress(String applicantAddress)
	{
		this.applicantAddress = applicantAddress;
	}

	/**
	 * Method 'getApplicantType'
	 * 
	 * @return String
	 */
	public String getApplicantType()
	{
		return applicantType;
	}

	/**
	 * Method 'setApplicantType'
	 * 
	 * @param applicantType
	 */
	public void setApplicantType(String applicantType)
	{
		this.applicantType = applicantType;
	}

	/**
	 * Method 'getAreaType'
	 * 
	 * @return String
	 */
	public String getAreaType()
	{
		return areaType;
	}

	/**
	 * Method 'setAreaType'
	 * 
	 * @param areaType
	 */
	public void setAreaType(String areaType)
	{
		this.areaType = areaType;
	}

	/**
	 * Method 'getApplicationDate'
	 * 
	 * @return Date
	 */
	public Date getApplicationDate()
	{
		return applicationDate;
	}

	/**
	 * Method 'setApplicationDate'
	 * 
	 * @param applicationDate
	 */
	public void setApplicationDate(Date applicationDate)
	{
		this.applicationDate = applicationDate;
	}

	/**
	 * Method 'getApplicationDateTime'
	 * 
	 * @return Date
	 */
	public Date getApplicationDateTime()
	{
		return applicationDateTime;
	}

	/**
	 * Method 'setApplicationDateTime'
	 * 
	 * @param applicationDateTime
	 */
	public void setApplicationDateTime(Date applicationDateTime)
	{
		this.applicationDateTime = applicationDateTime;
	}

	/**
	 * Method 'getDeedType'
	 * 
	 * @return int
	 */
	public int getDeedType()
	{
		return deedType;
	}

	/**
	 * Method 'setDeedType'
	 * 
	 * @param deedType
	 */
	public void setDeedType(int deedType)
	{
		this.deedType = deedType;
		this.deedTypeNull = false;
	}

	/**
	 * Method 'setDeedTypeNull'
	 * 
	 * @param value
	 */
	public void setDeedTypeNull(boolean value)
	{
		this.deedTypeNull = value;
	}

	/**
	 * Method 'isDeedTypeNull'
	 * 
	 * @return boolean
	 */
	public boolean isDeedTypeNull()
	{
		return deedTypeNull;
	}

	/**
	 * Method 'getDeedSubtype'
	 * 
	 * @return String
	 */
	public String getDeedSubtype()
	{
		return deedSubtype;
	}

	/**
	 * Method 'setDeedSubtype'
	 * 
	 * @param deedSubtype
	 */
	public void setDeedSubtype(String deedSubtype)
	{
		this.deedSubtype = deedSubtype;
	}

	/**
	 * Method 'getConsiderationAmount'
	 * 
	 * @return int
	 */
	public int getConsiderationAmount()
	{
		return considerationAmount;
	}

	/**
	 * Method 'setConsiderationAmount'
	 * 
	 * @param considerationAmount
	 */
	public void setConsiderationAmount(int considerationAmount)
	{
		this.considerationAmount = considerationAmount;
		this.considerationAmountNull = false;
	}

	/**
	 * Method 'setConsiderationAmountNull'
	 * 
	 * @param value
	 */
	public void setConsiderationAmountNull(boolean value)
	{
		this.considerationAmountNull = value;
	}

	/**
	 * Method 'isConsiderationAmountNull'
	 * 
	 * @return boolean
	 */
	public boolean isConsiderationAmountNull()
	{
		return considerationAmountNull;
	}

	/**
	 * Method 'getRegistrationFee'
	 * 
	 * @return int
	 */
	public int getRegistrationFee()
	{
		return registrationFee;
	}

	/**
	 * Method 'setRegistrationFee'
	 * 
	 * @param registrationFee
	 */
	public void setRegistrationFee(int registrationFee)
	{
		this.registrationFee = registrationFee;
		this.registrationFeeNull = false;
	}

	/**
	 * Method 'setRegistrationFeeNull'
	 * 
	 * @param value
	 */
	public void setRegistrationFeeNull(boolean value)
	{
		this.registrationFeeNull = value;
	}

	/**
	 * Method 'isRegistrationFeeNull'
	 * 
	 * @return boolean
	 */
	public boolean isRegistrationFeeNull()
	{
		return registrationFeeNull;
	}

	/**
	 * Method 'getStampDuty'
	 * 
	 * @return int
	 */
	public int getStampDuty()
	{
		return stampDuty;
	}

	/**
	 * Method 'setStampDuty'
	 * 
	 * @param stampDuty
	 */
	public void setStampDuty(int stampDuty)
	{
		this.stampDuty = stampDuty;
		this.stampDutyNull = false;
	}

	/**
	 * Method 'setStampDutyNull'
	 * 
	 * @param value
	 */
	public void setStampDutyNull(boolean value)
	{
		this.stampDutyNull = value;
	}

	/**
	 * Method 'isStampDutyNull'
	 * 
	 * @return boolean
	 */
	public boolean isStampDutyNull()
	{
		return stampDutyNull;
	}

	/**
	 * Method 'getDocumentToBeFurnished'
	 * 
	 * @return String
	 */
	public String getDocumentToBeFurnished()
	{
		return documentToBeFurnished;
	}

	/**
	 * Method 'setDocumentToBeFurnished'
	 * 
	 * @param documentToBeFurnished
	 */
	public void setDocumentToBeFurnished(String documentToBeFurnished)
	{
		this.documentToBeFurnished = documentToBeFurnished;
	}

	/**
	 * Method 'getDocSubject'
	 * 
	 * @return String
	 */
	public String getDocSubject()
	{
		return docSubject;
	}

	/**
	 * Method 'setDocSubject'
	 * 
	 * @param docSubject
	 */
	public void setDocSubject(String docSubject)
	{
		this.docSubject = docSubject;
	}

	/**
	 * Method 'getWhetherLand'
	 * 
	 * @return short
	 */
	public short getWhetherLand()
	{
		return whetherLand;
	}

	/**
	 * Method 'setWhetherLand'
	 * 
	 * @param whetherLand
	 */
	public void setWhetherLand(short whetherLand)
	{
		this.whetherLand = whetherLand;
	}

	/**
	 * Method 'getGender'
	 * 
	 * @return String
	 */
	public String getGender()
	{
		return gender;
	}

	/**
	 * Method 'setGender'
	 * 
	 * @param gender
	 */
	public void setGender(String gender)
	{
		this.gender = gender;
	}

	/**
	 * Method 'getAct'
	 * 
	 * @return String
	 */
	public String getAct()
	{
		return act;
	}

	/**
	 * Method 'setAct'
	 * 
	 * @param act
	 */
	public void setAct(String act)
	{
		this.act = act;
	}

	/**
	 * Method 'getSroOffice'
	 * 
	 * @return int
	 */
	public int getSroOffice()
	{
		return sroOffice;
	}

	/**
	 * Method 'setSroOffice'
	 * 
	 * @param sroOffice
	 */
	public void setSroOffice(int sroOffice)
	{
		this.sroOffice = sroOffice;
	}

	/**
	 * Method 'getAppointmentDate'
	 * 
	 * @return Date
	 */
	public Date getAppointmentDate()
	{
		return appointmentDate;
	}

	/**
	 * Method 'setAppointmentDate'
	 * 
	 * @param appointmentDate
	 */
	public void setAppointmentDate(Date appointmentDate)
	{
            System.out.println("appointmentDate------DTO"+appointmentDate);
		this.appointmentDate = appointmentDate;
	}

	/**
	 * Method 'getAppointmentTimeSlot'
	 * 
	 * @return Date
	 */
	public Date getAppointmentTimeSlot()
	{
		return appointmentTimeSlot;
	}

	/**
	 * Method 'setAppointmentTimeSlot'
	 * 
	 * @param appointmentTimeSlot
	 */
	public void setAppointmentTimeSlot(Date appointmentTimeSlot)
	{
		this.appointmentTimeSlot = appointmentTimeSlot;
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
		
		if (!(_other instanceof AppointmentSlotBooking)) {
			return false;
		}
		
		final AppointmentSlotBooking _cast = (AppointmentSlotBooking) _other;
		if (slno != _cast.slno) {
			return false;
		}
		
		if (slnoNull != _cast.slnoNull) {
			return false;
		}
		
		if (appointmentId == null ? _cast.appointmentId != appointmentId : !appointmentId.equals( _cast.appointmentId )) {
			return false;
		}
		
		if (officerId != _cast.officerId) {
			return false;
		}
		
		if (applicantName == null ? _cast.applicantName != applicantName : !applicantName.equals( _cast.applicantName )) {
			return false;
		}
		
		if (email == null ? _cast.email != email : !email.equals( _cast.email )) {
			return false;
		}
		
		if (mobileNumber == null ? _cast.mobileNumber != mobileNumber : !mobileNumber.equals( _cast.mobileNumber )) {
			return false;
		}
		
		if (applicantAddress == null ? _cast.applicantAddress != applicantAddress : !applicantAddress.equals( _cast.applicantAddress )) {
			return false;
		}
		
		if (applicantType == null ? _cast.applicantType != applicantType : !applicantType.equals( _cast.applicantType )) {
			return false;
		}
		
		if (areaType == null ? _cast.areaType != areaType : !areaType.equals( _cast.areaType )) {
			return false;
		}
		
		if (applicationDate == null ? _cast.applicationDate != applicationDate : !applicationDate.equals( _cast.applicationDate )) {
			return false;
		}
		
		if (applicationDateTime == null ? _cast.applicationDateTime != applicationDateTime : !applicationDateTime.equals( _cast.applicationDateTime )) {
			return false;
		}
		
		if (deedType != _cast.deedType) {
			return false;
		}
		
		if (deedTypeNull != _cast.deedTypeNull) {
			return false;
		}
		
		if (deedSubtype == null ? _cast.deedSubtype != deedSubtype : !deedSubtype.equals( _cast.deedSubtype )) {
			return false;
		}
		
		if (considerationAmount != _cast.considerationAmount) {
			return false;
		}
		
		if (considerationAmountNull != _cast.considerationAmountNull) {
			return false;
		}
		
		if (registrationFee != _cast.registrationFee) {
			return false;
		}
		
		if (registrationFeeNull != _cast.registrationFeeNull) {
			return false;
		}
		
		if (stampDuty != _cast.stampDuty) {
			return false;
		}
		
		if (stampDutyNull != _cast.stampDutyNull) {
			return false;
		}
		
		if (documentToBeFurnished == null ? _cast.documentToBeFurnished != documentToBeFurnished : !documentToBeFurnished.equals( _cast.documentToBeFurnished )) {
			return false;
		}
		
		if (docSubject == null ? _cast.docSubject != docSubject : !docSubject.equals( _cast.docSubject )) {
			return false;
		}
		
		if (whetherLand != _cast.whetherLand) {
			return false;
		}
		
		if (gender == null ? _cast.gender != gender : !gender.equals( _cast.gender )) {
			return false;
		}
		
		if (act == null ? _cast.act != act : !act.equals( _cast.act )) {
			return false;
		}
		
		if (sroOffice != _cast.sroOffice) {
			return false;
		}
		
		if (appointmentDate == null ? _cast.appointmentDate != appointmentDate : !appointmentDate.equals( _cast.appointmentDate )) {
			return false;
		}
		
		if (appointmentTimeSlot == null ? _cast.appointmentTimeSlot != appointmentTimeSlot : !appointmentTimeSlot.equals( _cast.appointmentTimeSlot )) {
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
		if (appointmentId != null) {
			_hashCode = 29 * _hashCode + appointmentId.hashCode();
		}
		
		_hashCode = 29 * _hashCode + officerId;
		if (applicantName != null) {
			_hashCode = 29 * _hashCode + applicantName.hashCode();
		}
		
		if (email != null) {
			_hashCode = 29 * _hashCode + email.hashCode();
		}
		
		if (mobileNumber != null) {
			_hashCode = 29 * _hashCode + mobileNumber.hashCode();
		}
		
		if (applicantAddress != null) {
			_hashCode = 29 * _hashCode + applicantAddress.hashCode();
		}
		
		if (applicantType != null) {
			_hashCode = 29 * _hashCode + applicantType.hashCode();
		}
		
		if (areaType != null) {
			_hashCode = 29 * _hashCode + areaType.hashCode();
		}
		
		if (applicationDate != null) {
			_hashCode = 29 * _hashCode + applicationDate.hashCode();
		}
		
		if (applicationDateTime != null) {
			_hashCode = 29 * _hashCode + applicationDateTime.hashCode();
		}
		
		_hashCode = 29 * _hashCode + deedType;
		_hashCode = 29 * _hashCode + (deedTypeNull ? 1 : 0);
		if (deedSubtype != null) {
			_hashCode = 29 * _hashCode + deedSubtype.hashCode();
		}
		
		_hashCode = 29 * _hashCode + considerationAmount;
		_hashCode = 29 * _hashCode + (considerationAmountNull ? 1 : 0);
		_hashCode = 29 * _hashCode + registrationFee;
		_hashCode = 29 * _hashCode + (registrationFeeNull ? 1 : 0);
		_hashCode = 29 * _hashCode + stampDuty;
		_hashCode = 29 * _hashCode + (stampDutyNull ? 1 : 0);
		if (documentToBeFurnished != null) {
			_hashCode = 29 * _hashCode + documentToBeFurnished.hashCode();
		}
		
		if (docSubject != null) {
			_hashCode = 29 * _hashCode + docSubject.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) whetherLand;
		if (gender != null) {
			_hashCode = 29 * _hashCode + gender.hashCode();
		}
		
		if (act != null) {
			_hashCode = 29 * _hashCode + act.hashCode();
		}
		
		_hashCode = 29 * _hashCode + sroOffice;
		if (appointmentDate != null) {
			_hashCode = 29 * _hashCode + appointmentDate.hashCode();
		}
		
		if (appointmentTimeSlot != null) {
			_hashCode = 29 * _hashCode + appointmentTimeSlot.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return AppointmentSlotBookingPk
	 */
	public AppointmentSlotBookingPk createPk()
	{
		return new AppointmentSlotBookingPk(appointmentId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "org.nic.epanjeeyan.dto.AppointmentSlotBooking: " );
		ret.append( "slno=" + slno );
		ret.append( ", appointmentId=" + appointmentId );
		ret.append( ", officerId=" + officerId );
		ret.append( ", applicantName=" + applicantName );
		ret.append( ", email=" + email );
		ret.append( ", mobileNumber=" + mobileNumber );
		ret.append( ", applicantAddress=" + applicantAddress );
		ret.append( ", applicantType=" + applicantType );
		ret.append( ", areaType=" + areaType );
		ret.append( ", applicationDate=" + applicationDate );
		ret.append( ", applicationDateTime=" + applicationDateTime );
		ret.append( ", deedType=" + deedType );
		ret.append( ", deedSubtype=" + deedSubtype );
		ret.append( ", considerationAmount=" + considerationAmount );
		ret.append( ", registrationFee=" + registrationFee );
		ret.append( ", stampDuty=" + stampDuty );
		ret.append( ", documentToBeFurnished=" + documentToBeFurnished );
		ret.append( ", docSubject=" + docSubject );
		ret.append( ", whetherLand=" + whetherLand );
		ret.append( ", gender=" + gender );
		ret.append( ", act=" + act );
		ret.append( ", sroOffice=" + sroOffice );
		ret.append( ", appointmentDate=" + appointmentDate );
		ret.append( ", appointmentTimeSlot=" + appointmentTimeSlot );
		return ret.toString();
	}

}