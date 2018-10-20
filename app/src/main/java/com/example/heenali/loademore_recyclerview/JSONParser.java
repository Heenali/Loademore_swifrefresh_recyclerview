package com.example.heenali.loademore_recyclerview;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static JSONArray jArry = null;
	static String json = "";
	String charset = "UTF-8";
	HttpURLConnection conn;
	DataOutputStream wr;
	StringBuilder result = new StringBuilder();
	URL urlObj;
	// JSONObject jObj = null;
	StringBuilder sbParams;
	String paramsString;
	private static final String USER_AGENT = "Mozilla/5.0";
	public JSONParser() {
	}


	// HTTP GET request
	public String sendGet(String url){
	String res="";
		//String url = "http://www.google.com/search?q=mkyong";
		try {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("User-Agent", USER_AGENT);
		//conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

			if(in != null)
				in.close();
			res = response.toString();
		} catch (IOException e) {
			e.printStackTrace();

			//print result

		}

		System.out.println(res);
	return  res;
	}

	/*@SuppressWarnings("deprecation")
	public String getJSONFromUrl(String url) {

		try {

			HttpGet httpGet = new HttpGet(url);
			HttpParams httpParameters = new BasicHttpParams();
			int timeoutConnection = 90000;
			HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
			int timeoutSocket = 90000;
			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

			DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
			HttpResponse httpResponse =httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

		} catch (ConnectTimeoutException e) {
			return "lost";
		} catch (UnsupportedEncodingException e) {
			return "lost";
		} catch (ClientProtocolException e) {
			return "lost";
		} catch (IOException e) {
			return "lost";
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		//	Log.e("Response>>>>>",json+"");
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		// return JSON String
		return json;

	}
	public String getJSONFromUrl1(String url) {

		try {

			HttpPost httpGet = new HttpPost(url);
			HttpParams httpParameters = new BasicHttpParams();
			int timeoutConnection = 90000;
			HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
			int timeoutSocket = 90000;
			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

			DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
			HttpResponse httpResponse =httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

		} catch (ConnectTimeoutException e) {
			return "lost";
		} catch (UnsupportedEncodingException e) {
			return "lost";
		} catch (ClientProtocolException e) {
			return "lost";
		} catch (IOException e) {
			return "lost";
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
			Log.e("Response>>>>>",json+"");
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		// return JSON String
		return json;

	}*/

	@SuppressWarnings({ "deprecation", "deprecation" })
	public String sendJSONPost(String URL, JSONObject json1)
	{
		String res="";
		String response = "";
		//String jsonstatic="{\"truck_id\": \"tt1\",\"reg_date\": \"12/11/15\",\"truck_make_id\": \"0001\",\"truck_model\": \"001\",\"year_of_mfg\": \"2015\",\"load_capacity\": \"0-3\",\"axle_detail\": \"001\",\"body_type\": \"0001\",\"current_millage\": \"25\",\"avg_millage_per_month\": \"22\",\"fuel_average\": \"18\",\"active_flag\": \"Y\",\"created_by\": \"fdfs\",\"device_id\": \"123\",\"device_type\": \"456\"}";
		try {
			urlObj = new URL(URL);

			conn = (HttpURLConnection) urlObj.openConnection();

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			// application/x-www-form-urlencoded
			//conn.setRequestProperty("Accept-Charset", "application/json;charset=utf-8");
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

			conn.setReadTimeout(90000);
			conn.setConnectTimeout(90000);

			conn.connect();

			// paramsString = sbParams.toString();

			wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(json1.toString());
			wr.flush();
			wr.close();



		} catch (IOException e) {
			e.printStackTrace();
		}

		try {


			StringBuilder sb = new StringBuilder();
			int HttpResult = conn.getResponseCode(); 
			Log.e("Code>>", HttpResult+"");

			InputStream errorstream = conn.getErrorStream();

			BufferedReader br = null;
			if (errorstream == null){
				InputStream inputstream = conn.getInputStream();
				br = new BufferedReader(new InputStreamReader(inputstream));
			}else{
				br = new BufferedReader(new InputStreamReader(errorstream));
			}

			String nachricht;
			while ((nachricht = br.readLine()) != null){
				response += nachricht;
			}
			Log.e("response>>",response.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.disconnect();

		return response;
	}
	public String sendJSONPost1(String URL)
	{
		String res="";
		String response = "";
		String jsonstatic="{\"truck_id\": \"tt1\",\"reg_date\": \"12/11/15\",\"truck_make_id\": \"0001\",\"truck_model\": \"001\",\"year_of_mfg\": \"2015\",\"load_capacity\": \"0-3\",\"axle_detail\": \"001\",\"body_type\": \"0001\",\"current_millage\": \"25\",\"avg_millage_per_month\": \"22\",\"fuel_average\": \"18\",\"active_flag\": \"Y\",\"created_by\": \"fdfs\",\"device_id\": \"123\",\"device_type\": \"456\"}";
		try {
			urlObj = new URL(URL);

			conn = (HttpURLConnection) urlObj.openConnection();

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			// application/x-www-form-urlencoded
			//conn.setRequestProperty("Accept-Charset", "application/json;charset=utf-8");
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

			conn.setReadTimeout(90000);
			conn.setConnectTimeout(90000);

			conn.connect();

			// paramsString = sbParams.toString();

			wr = new DataOutputStream(conn.getOutputStream());
			//  wr.writeBytes(json1.toString());
			wr.flush();
			wr.close();



		} catch (IOException e) {
			e.printStackTrace();
		}

		try {


			StringBuilder sb = new StringBuilder();
			int HttpResult = conn.getResponseCode(); 
			Log.e("Code>>", HttpResult+"");

			InputStream errorstream = conn.getErrorStream();

			BufferedReader br = null;
			if (errorstream == null){
				InputStream inputstream = conn.getInputStream();
				br = new BufferedReader(new InputStreamReader(inputstream));
			}else{
				br = new BufferedReader(new InputStreamReader(errorstream));
			}

			String nachricht;
			while ((nachricht = br.readLine()) != null){
				response += nachricht;
			}
			Log.e("response>>",response.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.disconnect();

		return response;
	}

	/*public String getJSONArryFromUrl(String url) {

		// Making HTTP request
		try {
			// defaultHttpClient
			HttpGet httpGet = new HttpGet(url);
			HttpParams httpParameters = new BasicHttpParams();
			int timeoutConnection = 20000;
			HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
			int timeoutSocket = 20000;
			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
			DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
			HttpResponse httpResponse =httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

		} catch (ConnectTimeoutException e) {
			return "lost";
		} catch (UnsupportedEncodingException e) {
			return "lost";
		} catch (ClientProtocolException e) {
			return "lost";
		} catch (IOException e) {
			return "lost";
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
			Log.e("JSON", json);
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}


		// return JSON String
		return json;

	}*/

}
