package Functionality;
import java.util.*;
public class Authentication {
	
	Scanner sc = new Scanner(System.in);
	public boolean passwordAuth(String password) {
		int len = password.length();
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

	public boolean otpAuth(String email, boolean isEmail) {
		// logic to generate and send otp 
		return true;
	}

	public boolean panAuth(String pan) {
		// logic to authenticate PAN
		return true;
	}

	public boolean adhaarAuth(String adhaar) {
		// logic to authenticate Adhaar
		return true;
	}

	public boolean bankAuth(String IFSC, String MICR, long acc_no) {
		// logic to authentciate bank details
		return true;
	}
	
	public boolean AdminAuth() {
		String password;
		System.out.println("Enter admin password");
		password = sc.next();
		User user = new User();
		String admin_password = user.getAdmin_password();
		if(admin_password.equals(password))
			return true;
		else
			return false;
	}

}
