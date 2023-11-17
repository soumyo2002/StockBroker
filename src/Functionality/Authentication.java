package Functionality;
import java.util.regex.*;

// Class for handling authentication procedures
import java.util.*;
public class Authentication {
	
	Scanner sc = new Scanner(System.in);
	// Method to authenticate a password based on specified criteria
	public boolean passwordAuth(String password) {
		int len = password.length();
		//Criteria set for password
		//Length of password must be 8 characters
		//It must have atleast one upper and lowercase character,atleast one digit and special character
		//It should not have whitespaces
		    if (len <= 8) {
		        System.out.println("Password length must be greater than 8 characters");
		        return false;
		    } else if (!password.matches(".*[a-z].*")) {
		        System.out.println("Password must contain at least one lowercase letter");
		        return false;
		    } else if (!password.matches(".*[A-Z].*")) {
		        System.out.println("Password must contain at least one uppercase letter");
		        return false;
		    } else if (!password.matches(".*\\d.*")) {
		        System.out.println("Password must contain at least one number");
		        return false;
		    } else if (!password.matches(".*[!@#$%^&*].*")) {
		        System.out.println("Password must contain at least one special character (!, @, #, $, %, ^, &, *)");
		        return false;
		    } else if (password.matches(".*[\\s].*")) {
		        System.out.println("Password cannot contain any whitespace");
		        return false;
		    }
		    return true;
		    
		    
		}
	 // Method to authenticate an OTP based on the provided string and indication if it's an email or phone number
	public boolean otpAuth(String str, boolean isEmail) {
		
		if(isEmail) {
		//Email ID must have a @ symbol
		 String regex = "^(.+)@(.+)$";  
		 Pattern pattern = Pattern.compile(regex);  
		 Matcher matcher = pattern.matcher(str);
		 if(matcher.matches())
			 return true;
		 else
			 System.out.println("Invalid Email ID!Format for email is user@domain.com");
		}else {
			int len = str.length();
			//Phone number must have 10 digits
			if(len == 10 && str.matches("\\d+"))
				return true;
			else
				 System.out.println("Invalid Phone number!It must be 10 digits in length");
		}
		return false;
		
		//Further we can add logic to generate and send otp 
	}

	// Method to authenticate a PAN (Permanent Account Number)
	public boolean panAuth(String pan) {
		// logic to authenticate PAN
		//Rules for PAN Number
		//Starting 5 characters are uppercase alphabets
		//Next 4 characters are digits
		//Ending with one uppercase character
		String regrex = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$";
		Pattern pattern = Pattern.compile(regrex);
		Matcher matcher = pattern.matcher(pan);
		if(matcher.matches())
			return true;
		else
			System.out.println("Invalid PAN number!It must have first 5 characters as capital letters,"
					+ "followed by 4 numbers between 0-9 and finally ending with a uppercase character");
		return false;
	}

	// Method to authenticate a Adhaar
	public boolean adhaarAuth(String adhaar) {
		// logic to authenticate Adhaar
		//Starting digit should range from 2-9 and rest 11 digits must be between 0-9
		String regrex = "^[2-9][0-9]{11}$";
		Pattern pattern = Pattern.compile(regrex);
		Matcher matcher = pattern.matcher(adhaar);
		if(matcher.matches())
			return true;
		else
			System.out.println("Invalid Adhaar number!It must 12 characters long and first digit must be between 2-9.");
		return false;
	}

	// Method to authenticate a Bank details
	public boolean bankAuth(String IFSC, String MICR, long acc_no) {
		// logic to authenticate bank details
		String str = String.valueOf(acc_no);
		//Account numbers are generally between 9-18 digits in length
		String regrex = "^[0-9]{9,18}$";
		Pattern pattern = Pattern.compile(regrex);
		Matcher matcher = pattern.matcher(str);
		if(matcher.matches())
			return true;
		else
			System.out.println("Invalid account number!length of account number ranges from 9 to 18");
		return false;
	}
	
	// Method to authenticate admin privileges
	public boolean AdminAuth() {
		String password;
		System.out.println("Enter admin password");
		password = sc.next();
		User user = new User();
		String admin_password = user.getAdmin_password();
		if(admin_password.equals(password)) {
			System.out.println("Authentication successful!");
			return true;
		}
		else {
			return false;
		}
	}

}
