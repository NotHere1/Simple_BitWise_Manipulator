/**
 * 
 */

/**
 * @author jusanng
 *
 */
public class Rdt_Header {
	
	/**
	 * Convert the first two byte of Java's four bytes int to a two bytes array
	 * value are store in big-endian order
	 * @param value an int
	 * @return a two bytes byte array
	 */
	public static byte[] intToTwoByteArray (final int value) {
		byte[] result = new byte[2];
		
		result[0] = (byte)((value & 0xFF00) >> 8);
		result[1] = (byte)((value & 0x00FF));
		
		return result;
	}
	
	/**
	 * Convert Java's four bytes int to a four bytes array representation.
	 * Value are store in big-endian order
	 * @param integer an int
	 * @return a four bytes byte array
	 */
	public static byte[] intToFourByteArray (final int integer) {  
	    byte[] result = new byte[4];  
	   
	    result[0] = (byte)((integer & 0xFF000000) >> 24);  
	    result[1] = (byte)((integer & 0x00FF0000) >> 16);  
	    result[2] = (byte)((integer & 0x0000FF00) >> 8);  
	    result[3] = (byte)(integer & 0x000000FF);  
	     
	    return result;
	}
	
	/**
	 * Convert byte array that store value information back to int
	 * @param arr byte array
	 * @param byte_length length of the byte array
	 * @return the int value containing in the byte array
	 */
	public static int byteArrayToInt (final byte[] arr, int byte_length ) {
		
		int value = 0;
		
		if (byte_length == 4){
			value = ((arr[0] & 0xFF) << 24) | ((arr[1] & 0xFF) << 16)
					| ((arr[2] & 0xFF) << 8) | (arr[3] & 0xFF);
		}
		
		if (byte_length == 2)
			value = ((arr[0] & 0xFF) << 8) | ((arr[1] & 0xFF));
			
		return value;	
	}
	
	/**
	 * Set the ack flag option to 1 in the option portion of the rdt header
	 * 00[urg][ack] [psh][rst][syn][fin] ~ 0000 0000
	 * @param option_buf the 2 bytes option segment in rdt header
	 * @param turnOn true - turn flag on
	 */
	public static void set_flag_ack(byte[] option_buf) {	
		// change the least significant byte
		// not need to return since java pass object by references!)
		option_buf[1] = (byte) (option_buf[1] | 00010000);
		System.out.println("set ACK sucessful");
	}
	
	/**
	 * Turn on the fin flag
	 * @param option_buf option buf
	 * @param turnOn true - turn flag on
	 */
	public static void set_flag_fin(byte[] option_buf) {
		option_buf[1] = (byte) (option_buf[1] | 00000001);
		System.out.println("setting fin flag");
		
		if ((byte)(option_buf[1] & 0x1) == 0x1){
			System.out.println("set FIN successful");
		}
	}
	
	/**
	 * Turn on the syn flag
	 * @param option_buf option_buf
	 * @param turnOn true - turn flag on
	 */
	public static void set_flag_syn(byte[] option_buf) {
		option_buf[1] = (byte) (option_buf[1] | 00000010);
	}
}
