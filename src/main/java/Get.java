

/**
 * 
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * @author bhahuja
 *
 */
public class Get {

	
	private final String USER_AGENT = "bhavneet";
	
		public static void main(String[] args) {

			Get http = new Get();
			 
			
			try {
				http.sendGet();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
		
		
		/**
		 * @throws Exception
		 */
		
		private void sendGet() throws Exception {
			 
			String url = "https://gb.chaloapp.com/api/v1/restaurants";
	 
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 

			con.setRequestMethod("GET");
	 

			con.setRequestProperty("User-Agent", USER_AGENT);
	 

			System.out.println("\nSending 'GET' request to URL : " + url);

	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			System.out.println("\n");
			String inputLine;
			StringBuffer	 response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			con.disconnect();
			
			System.out.println(response.toString());
			String s=response.toString();
			
			JSONArray responseArray = new JSONArray(s);
			JSONObject jobj = new JSONObject();
			boolean projectname;
			int restID=0;
			FileWriter file=new FileWriter("output.txt");
			PrintWriter printOutput=new PrintWriter(file);
			
			for(int i=0;i<responseArray.length();i++)
			{
				jobj = responseArray.getJSONObject(i);
				projectname = jobj.getBoolean("demo");
				restID=jobj.getInt("restaurant_id");
				if(projectname){
					System.out.println("\n"+restID);
					printOutput.println(restID);
				}
			}
			printOutput.close();
		}

}
