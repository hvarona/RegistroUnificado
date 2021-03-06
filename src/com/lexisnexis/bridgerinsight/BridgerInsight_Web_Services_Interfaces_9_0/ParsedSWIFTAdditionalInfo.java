/**
 * ParsedSWIFTAdditionalInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0;

public class ParsedSWIFTAdditionalInfo  implements java.io.Serializable {
    private java.lang.String country;

    private java.lang.String info;

    private java.lang.String parsedInfo;

    private com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.AdditionalInfoType type;

    public ParsedSWIFTAdditionalInfo() {
    }

    public ParsedSWIFTAdditionalInfo(
           java.lang.String country,
           java.lang.String info,
           java.lang.String parsedInfo,
           com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.AdditionalInfoType type) {
           this.country = country;
           this.info = info;
           this.parsedInfo = parsedInfo;
           this.type = type;
    }


    /**
     * Gets the country value for this ParsedSWIFTAdditionalInfo.
     * 
     * @return country
     */
    public java.lang.String getCountry() {
        return country;
    }


    /**
     * Sets the country value for this ParsedSWIFTAdditionalInfo.
     * 
     * @param country
     */
    public void setCountry(java.lang.String country) {
        this.country = country;
    }


    /**
     * Gets the info value for this ParsedSWIFTAdditionalInfo.
     * 
     * @return info
     */
    public java.lang.String getInfo() {
        return info;
    }


    /**
     * Sets the info value for this ParsedSWIFTAdditionalInfo.
     * 
     * @param info
     */
    public void setInfo(java.lang.String info) {
        this.info = info;
    }


    /**
     * Gets the parsedInfo value for this ParsedSWIFTAdditionalInfo.
     * 
     * @return parsedInfo
     */
    public java.lang.String getParsedInfo() {
        return parsedInfo;
    }


    /**
     * Sets the parsedInfo value for this ParsedSWIFTAdditionalInfo.
     * 
     * @param parsedInfo
     */
    public void setParsedInfo(java.lang.String parsedInfo) {
        this.parsedInfo = parsedInfo;
    }


    /**
     * Gets the type value for this ParsedSWIFTAdditionalInfo.
     * 
     * @return type
     */
    public com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.AdditionalInfoType getType() {
        return type;
    }


    /**
     * Sets the type value for this ParsedSWIFTAdditionalInfo.
     * 
     * @param type
     */
    public void setType(com.lexisnexis.bridgerinsight.BridgerInsight_Web_Services_Interfaces_9_0.AdditionalInfoType type) {
        this.type = type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParsedSWIFTAdditionalInfo)) return false;
        ParsedSWIFTAdditionalInfo other = (ParsedSWIFTAdditionalInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.country==null && other.getCountry()==null) || 
             (this.country!=null &&
              this.country.equals(other.getCountry()))) &&
            ((this.info==null && other.getInfo()==null) || 
             (this.info!=null &&
              this.info.equals(other.getInfo()))) &&
            ((this.parsedInfo==null && other.getParsedInfo()==null) || 
             (this.parsedInfo!=null &&
              this.parsedInfo.equals(other.getParsedInfo()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        if (getInfo() != null) {
            _hashCode += getInfo().hashCode();
        }
        if (getParsedInfo() != null) {
            _hashCode += getParsedInfo().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParsedSWIFTAdditionalInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://bridgerinsight.lexisnexis.com/BridgerInsight.Web.Services.Interfaces.9.0", "ParsedSWIFTAdditionalInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("https://bridgerinsight.lexisnexis.com/BridgerInsight.Web.Services.Interfaces.9.0", "Country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("info");
        elemField.setXmlName(new javax.xml.namespace.QName("https://bridgerinsight.lexisnexis.com/BridgerInsight.Web.Services.Interfaces.9.0", "Info"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parsedInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("https://bridgerinsight.lexisnexis.com/BridgerInsight.Web.Services.Interfaces.9.0", "ParsedInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("https://bridgerinsight.lexisnexis.com/BridgerInsight.Web.Services.Interfaces.9.0", "Type"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://bridgerinsight.lexisnexis.com/BridgerInsight.Web.Services.Interfaces.9.0", "AdditionalInfoType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
