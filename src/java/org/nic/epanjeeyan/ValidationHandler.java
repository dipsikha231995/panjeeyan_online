/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nic.epanjeeyan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ValidationHandler {
    private ArrayList<String> errors = new ArrayList();

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }
    
    public void validate(Object input, String fieldName, String paramList) {
        
        String[] params;
        if(paramList.contains(":")){
            params = paramList.split(":");
        }
        else {
            params = new String[1];
            params[0] = paramList;
        }
        
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals("date")) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false);
                try {
                    sdf.parse(input.toString());
                } catch (ParseException e) {
                    errors.add("Invalid date format for the field <strong>" + fieldName + "</strong>");
                    break;
                }
            }
            else if (params[i].equals("required")) {
                if (input == null) {
                    System.out.println("<strong>" + fieldName + "</strong> is a required field.");
                    errors.add(" <strong>" + fieldName + "</strong> is a required field.");
                    break;
                }
                else if (input.toString().isEmpty()) {
                    System.out.println("<strong>" + fieldName + "</strong> is a required field.");
                    errors.add(" <strong>" + fieldName + "</strong> is a required field.");
                    break;
                }
                
            }
            
            else if (params[i].equals("numeric")) {
                if(input == null) {
                    System.out.println("Invalid numeric value for supplied <strong>" + fieldName + "</strong>.");
                    errors.add("Invalid numeric value for supplied <strong>" + fieldName + "</strong>.");
                    break;
                }
                else {
                    try {
                        Integer.parseInt(input.toString());
                    } catch(NumberFormatException e) { 
                        errors.add("Invalid numeric value for supplied <strong>" + fieldName + "</strong>.");
                        break;
                    }   
                }
            }
            else if (params[i].contains("low")) {
                int val = Integer.valueOf(params[i].substring((params[i].indexOf('_') + 1), params[i].length()));
                if(input == null) {
                    System.out.println("The minimum value of the field <strong>" + fieldName + "</strong> should be " + val );
                    errors.add("The minimum value of the field <strong>" + fieldName + "</strong> should be " + val);
                    break;
                }
                else if (Integer.parseInt(input.toString()) < val) {
                    System.out.println("The minimum value of the field <strong>" + fieldName + "</strong> should be " + val );
                    errors.add("The minimum value of the field <strong>" + fieldName + "</strong> should be " + val);
                    break;
                } 
            }
            else if (params[i].contains("high")) {
                int val = Integer.valueOf(params[i].substring((params[i].indexOf('_') + 1), params[i].length()));
                if(input == null) {
                    System.out.println("The maximum value of the field <strong>" + fieldName + "</strong> should be " + val );
                    errors.add("The maximum value of the field <strong>" + fieldName + "</strong> should be " + val);
                    break;
                }
                else if (Integer.parseInt(input.toString()) > val) {
                    System.out.println("The maximum value of the field <strong>" + fieldName + "</strong> should be " + val );
                    errors.add("The maximum value of the field <strong>" + fieldName + "</strong> should be " + val);
                    break;
                } 
            }
            else if (params[i].contains("max")) {
                Integer len = Integer.valueOf(params[i].substring((params[i].indexOf('_') + 1), params[i].length()));
                if(input == null) {
                    System.out.println("The field <strong>" + fieldName + "</strong> cannot have more than " + len + " characters");
                    errors.add("The field <strong>" + fieldName + "</strong> cannot have more than " + len + " characters");
                    break; 
                }
                else if (input.toString().length() > len) {
                    System.out.println("The field <strong>" + fieldName + "</strong> cannot have more than " + len + " characters");
                    errors.add("The field <strong>" + fieldName + "</strong> cannot have more than " + len + " characters");
                    break; 
                }
               
            }
            else if (params[i].contains("min")) {
                Integer len = Integer.valueOf(params[i].substring((params[i].indexOf('_') + 1), params[i].length()));
                if(input == null) {
                    System.out.println("The field <strong>" + fieldName + "</strong> should have minimum " + len + " characters");
                    errors.add("The field <strong>" + fieldName + "</strong> should have minimum " + len + " characters");
                    break; 
                }
                else if (input.toString().length() < len) {
                    System.out.println("The field <strong>" + fieldName + "</strong> should have minimum " + len + " characters");
                    errors.add("The field <strong>" + fieldName + "</strong> should have minimum " + len + " characters");
                    break;
                }
                
            }
            else if (params[i].contains("xss")) {

                Pattern pattern = Pattern.compile("^[ a-zA-Z0-9()_@,#/$&.+-]*$");
                if(input == null) {
                    System.out.println("The field <strong>" + fieldName + "</strong> may contain only alphabets, numbers or any one of ':' , '(', ')', '_' , '@' , '#' , '$' , '&' , '.' , '+' , '-'");
                    errors.add("The field <strong>" + fieldName + "</strong> may contain only alphabets, numbers, white-space or any character of @ ( ) : _ , # . + - $ &");
                    break;
                }
                else if (!pattern.matcher(input.toString()).find()) {
                    System.out.println("The field <strong>" + fieldName + "</strong> may contain only alphabets, numbers or any one of ':' , '(', ')', '_' , '@' , '#' , '$' , '&' , '.' , '+' , '-'");
                    errors.add("The field <strong>" + fieldName + "</strong> may contain only alphabets, numbers, white-space or any character of @ ( ) : _ , # . + - $ &");
                    break;
                }
            }
            else if (params[i].contains("alphanum")) {
                Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
                if(input == null) {
                    System.out.println("The field <strong>" + fieldName + "</strong> may contain only alphabets and/or numbers");
                    errors.add("The field <strong>" + fieldName + "</strong> may contain only alphabets and/or numbers");
                    break;
                }
                else if (!pattern.matcher(input.toString()).find()) {
                    System.out.println("The field <strong>" + fieldName + "</strong> may contain only alphabets and/or numbers");
                    errors.add("The field <strong>" + fieldName + "</strong> may contain only alphabets and/or numbers");
                    break;
                }
            }
            else if (params[i].contains("sql")) {
                if(input == null) {
                    System.out.println("Invalid value for the field <strong>" + fieldName + "</strong>");
                    errors.add("Invalid value for the field <strong>" + fieldName + "</strong>");
                    break;
                }
                else {
                    String match[] = new String[]{"select", "insert", "delete", "drop", "update", "alter", "modify", "kill", "table", "fetch", "begin", "varchar"};
                    int flag = 0;

                    for (int k = 0; k < match.length; k++) {
                        if (input.toString().toLowerCase().contains(match[k])) {
                            flag = 1;
                        }

                    }
                    if (flag == 1) {
                        System.out.println("Invalid value for the field <strong>" + fieldName + "</strong>");
                        errors.add("Invalid value for the field <strong>" + fieldName + "</strong>");
                        break;
                    }
                }
                
            }
            
            else if (params[i].contains("captcha")) {
                Integer val = Integer.valueOf(params[i].substring((params[i].indexOf('_') + 1), params[i].length()));
                if(input == null) {
                    System.out.println("The result of addition is not correct."+val);
                    errors.add("The answer to the <strong>"+ fieldName + "</strong> is not correct.");
                    break;
                }
                else if (Integer.parseInt((String) input) != val) {
                    System.out.println("The result of addition is not correct."+val);
                    errors.add("The answer to the <strong>"+ fieldName + "</strong> is not correct.");
                    break;
                }
                
            }  
            else if (params[i].contains("email")) {
                if(input == null) {
                    errors.add("Invalid email address specified for the field <strong>"+ fieldName + "</strong>.");
                    break;
                }
                else if(input.toString().length() > 0){
                    try {
                        InternetAddress emailAddr = new InternetAddress ((String) input);
                        emailAddr.validate();
                    } catch (AddressException ex) {
                         System.out.println(ex.getMessage());
                         errors.add("Invalid email address specified for the field <strong>"+ fieldName + "</strong>.");
                         break;
                    } 
                } 
            }  
            else if (params[i].contains("mobile")) {
                if(input == null) {
                    System.out.println("Invalid mobile number specified for the field <strong>"+ fieldName + "</strong>.");
                    errors.add("Invalid mobile number specified for the field <strong>"+ fieldName + "</strong>.");
                    break;
                }
                else if(input.toString().length() > 0){
                    Pattern pattern = Pattern.compile("^\\d{10}$");
                    if (!pattern.matcher(input.toString()).find()) {
                        System.out.println("Invalid mobile number specified for the field <strong>"+ fieldName + "</strong>.");
                        errors.add("Invalid mobile number specified for the field <strong>"+ fieldName + "</strong>.");
                        break;
                    }
                }
            }
            else if (params[i].contains("PIN")) {
                if(input == null) {
                    System.out.println("Invalid PIN specified for the field <strong>"+ fieldName + "</strong>.");
                    errors.add("Invalid PIN specified for the field <strong>"+ fieldName + "</strong>.");
                    break;
                }
                else if(input.toString().length() > 0){
                    Pattern pattern = Pattern.compile("^\\d{6}$");
                    if (!pattern.matcher(input.toString()).find()) {
                        System.out.println("Invalid PIN specified for the field <strong>"+ fieldName + "</strong>.");
                        errors.add("Invalid PIN specified for the field <strong>"+ fieldName + "</strong>.");
                        break;
                    }
                }
            }
        }
    }
    
    public void validate(Object input, String fieldName, String paramList, String[] list) {
        
        String[] params;
        if(paramList.contains(":")){
            params = paramList.split(":");
        }
        else {
            params = new String[1];
            params[0] = paramList;
            
        }
        for (String param : params) {
            if (param.equals("valuein")) {
                if(!Arrays.asList(list).contains((String)input)) {
                    System.out.println("Value specified for field <strong>" + fieldName + "</strong> is not valid.");
                    errors.add("Value specified for field <strong>" + fieldName + "</strong> is not valid.");
                    break;
                }
            }
        }

    }
}
