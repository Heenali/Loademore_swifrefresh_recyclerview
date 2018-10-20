package com.example.heenali.loademore_recyclerview;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

public class UserFunctions
{

	private JSONParser jsonParser;
	// public static String URL = "http://test.trukker.ae/trukkerUAEApitest/Api/";  //live only
	public static String URL = "https://test.trukker.ae/trukkerUAEApitest/Api/";  //live only

//	public static String URLIMG=  "http://182.70.119.213/trukkeruae";
	public static String URLIMG=  "http://trukker.ae/trukkerUAEApi";

	private static String login_class = "authenticate";
	private static String method_login = "login_mob";

	private static String forgot_class = "forgot_password";
	private static String method_forgot = "forgot_password";

	private static String Masters_class = "masters";
	private static String method_Masters = "get_master_data";

	private static String Toagenda_class = "today_agenda_instructor";
	private static String method_Toagenda = "today_agenda_instructor";

	private static String Lesson_class = "check_lesson";
	private static String method_Lesson = "check_lesson_by_date";

	private static String ins_Avail_class = "instructor_availability";
	private static String method_ins_Avail = "insert_instructor_availability";

	private static String Avail_class = "instructor_availability";
	private static String method_Avail = "get_weekly_instructor_availability";

	private static String checklist_class = "field_users";
	private static String method_checklist = "get_building_checks";

	private static String select_class = "select";
	private static String method_select = "select_data";

	private static String update_class = "update";
	private static String method_update = "update_data";

	private static String insert_class = "insert";
	private static String method_add_data = "add_data";

	private static String message_class = "message";
	private static String method_message = "inbox_data";
	private static String method_message_data = "message_data";
	private static String table_inbox = "inbox";

	private static String payment_class = "payment";
	private static String method_paid = "payment_data_paid";

	private static String method_checkbyBuild = "get_checks_by_buildingid";
	private static String get_actual_times = "get_actual_times";

	Context context;

	// constructor
	public UserFunctions(Context context) {
		this.context = context;
		jsonParser = new JSONParser();

	}

	//  Login User
	public String LoginUser(String methodName, JSONObject jsonData) {
		// Building Parameters
		String urls = "";
		urls = URL + methodName;

		Log.e("Login Urljson", urls);
		String json = jsonParser.sendJSONPost(urls, jsonData);
		//	Log.e("new res", "res"+json);
		//String jsonFormattedString = json.replaceAll("\\\\", "");
		if (json.length() != 0) {
			json = json.trim();
			json = json.substring(1, json.length() - 1);
			json = json.replace("\\", "");
			Log.e("new res", json);
		}
		return json;
	}

	//for state
	public String TruckMakeMst(String methodName) {
		// Building Parameters
		String urls = "";


		urls = URL + methodName;

		Log.e("truckmake Urljson", urls);
		String json = jsonParser.sendGet(urls);
		//String jsonFormattedString = json.replaceAll("\\\\", "");
		json = json.trim();
		if (json.length() > 0) {
			json = json.substring(1, json.length() - 1);
			json = json.replace("\\", "");
			Log.e("new res", json);
		} else {
			json = "";
		}

		return json;
	}

	//gor get lat-long from pincode
	public String GetLatLongPincode(String methodName) {
		// Building Parameters
		String urls = "";


		urls = methodName;

		Log.e("truckmake Urljson", urls);
		String json = jsonParser.sendGet(urls);
		//String jsonFormattedString = json.replaceAll("\\\\", "");
		json = json.trim();
		if (json.length() > 0) {
			//json = json.substring(1, json.length() - 1);			// use for remove "," not neccessary for all service
			json = json.replace("\\", "");
			Log.e("new res", json);
		} else {
			json = "";
		}
		return json;
	}
	//  Login User
/*	public String DriverInqList(String methodName){
        // Building Parameters
		String urls="";


		urls = URL +methodName;

		Log.e("Login Urljson", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		//	Log.e("new res", "res"+json);
		//String jsonFormattedString = json.replaceAll("\\\\", "");
		if(json.length() >0)
		{
			json= json.trim();
			json = json.substring(1, json.length()-1);
			json = json.replace("\\", "");
			Log.e("new res", json);
		}
		return json;
	}

	public String DriverInqList1(String methodName){
		// Building Parameters
		String urls="";


		urls = URL +methodName;

		Log.e("Login Urljson", urls);
		String json = jsonParser.getJSONFromUrl1(urls);
		//	Log.e("new res", "res"+json);
		//String jsonFormattedString = json.replaceAll("\\\\", "");
		if(json.length() >0)
		{
			json= json.trim();
			json = json.substring(1, json.length()-1);
			json = json.replace("\\", "");
			Log.e("new res", json);
		}
		return json;
	}


	public String getTruckLocation(String methodName){
		// Building Parameters
		String urls="";


		urls = URL +methodName;

		Log.e("Login Urljson", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		//	Log.e("new res", "res"+json);
		//String jsonFormattedString = json.replaceAll("\\\\", "");
		if(json.length() >0)
		{
			json= json.trim();
			json = json.substring(1, json.length()-1);
			json = json.replace("\\", "");
			Log.e("new res", json);
		}
		return json;
	}*/

	public String RegisterUser(String methodName, JSONObject jsonData)
	{String json="";
		try
		{
			String urls = "";


			urls = URL + methodName;

			Log.e("Login Urljson", urls);
			json = jsonParser.sendJSONPost(urls, jsonData);


			if (json.length() > 0) {
				json = json.trim();
				json = json.substring(1, json.length() - 1);
				json = json.replace("\\", "");
				Log.e("new res", json);
			}

		}
		catch (Exception e)
		{

		}
		return json;
	}


	//Postload

	public String PostLoadData(String methodName, JSONObject jsonData) {
		// Building Parameters
		String urls = "";


		urls = URL + methodName;

		Log.e("PostLoad Urljson", urls);
		String json = jsonParser.sendJSONPost(urls, jsonData);
		//String jsonFormattedString = json.replaceAll("\\\\", "");
        /* json= json.trim();
		 json = json.substring(1, json.length()-1);
		 json = json.replace("\\", "");*/
		Log.e("new res", json);
		return json;
	}


	public void msg(String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	public void sendEmail(String email) {
		Log.i("Send email", "");
		final Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		final PackageManager pm = context.getPackageManager();
		final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
		ResolveInfo best = null;
		for (final ResolveInfo info : matches)
			if (info.activityInfo.packageName.endsWith(".gm") ||
					info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
		if (best != null)
			intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
		intent.setData(Uri.parse("vibhalbhatiya23@gmail.com"));
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_EMAIL, email);
		intent.putExtra(Intent.EXTRA_SUBJECT, "Error TrukkerLite");
		intent.putExtra(Intent.EXTRA_TEXT, email);
		context.startActivity(intent);
	}

	/*public String getInbox(String unm, String pass, int from, int to) {

		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", message_class));
		params.add(new BasicNameValuePair("method", method_message));
		params.add(new BasicNameValuePair("table_name", table_inbox));
		params.add(new BasicNameValuePair("from", from+""));
		params.add(new BasicNameValuePair("to", to+""));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("Inbox URL", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}

	public void demo(String demo) {
		Log.e("Demo URL", demo);
	}

	public String getSent(String unm, String pass, int from, int to) {
		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", message_class));
		params.add(new BasicNameValuePair("method", method_message_data));
		params.add(new BasicNameValuePair("table_name", table_inbox));
		params.add(new BasicNameValuePair("from", from+""));
		params.add(new BasicNameValuePair("to", to+""));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("Sent URL", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}

	public String getdraft(String unm, String pass, int from, int to) {
		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", message_class));
		params.add(new BasicNameValuePair("method", method_message_data));
		params.add(new BasicNameValuePair("table_name", table_inbox));
		params.add(new BasicNameValuePair("condition", "{\"draft_status\""+":"+"\""+"-1"+"\"}"));
		params.add(new BasicNameValuePair("from", from+""));
		params.add(new BasicNameValuePair("to", to+""));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("Draft URL", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}

	public String getCompetency(String unm, String pass, String app_id) {
		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", select_class));
		params.add(new BasicNameValuePair("method", method_select));
		params.add(new BasicNameValuePair("table_name", "student_competency_checklist"));
		params.add(new BasicNameValuePair("condition", "{\"app_id\""+":"+"\""+app_id+"\"}"));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("Competency URL", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}


	public String sendStudentUpdate(String unm, String pass, String values,String jsonUpdate) {
		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", "student"));
		params.add(new BasicNameValuePair("method", "student_data_edit"));
		params.add(new BasicNameValuePair("condition", values));
		params.add(new BasicNameValuePair("json", jsonUpdate));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("Order_method Update URL", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}


	public String getStudSchedule(String unm, String pass, String condition) {
		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", "student"));
		params.add(new BasicNameValuePair("method", "student_appointment_schedule"));
		params.add(new BasicNameValuePair("condition", condition));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("Order_method Schedule URL", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}

	public String getLessonStatus(String unm, String pass, String condition) {
		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", "lesson_checkin_checkout"));
		params.add(new BasicNameValuePair("method", "lesson_status"));
		params.add(new BasicNameValuePair("table_name", "lesson_checkin_checkout"));
		params.add(new BasicNameValuePair("condition", condition));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("Lesson Status URL", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}

	public String sendMSG(String unm, String pass, String jSon) {
		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", "send_message"));
		params.add(new BasicNameValuePair("method", "sms"));
		params.add(new BasicNameValuePair("json", jSon));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("Send MSg URL", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}

	public String getDailyMSG(String unm, String pass) {
		String urls="";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", "daily_msg"));
		params.add(new BasicNameValuePair("method", "daily_message_data"));
		params.add(new BasicNameValuePair("table_name", "daily_message"));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}

		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("URLS Daily Message", urls);
		String jsonStr = jsonParser.getJSONFromUrl(urls);
		return jsonStr;
	}
	public String getLessonHistory(String unm, String pass, String condition) {
		String urls="";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", "student"));
		params.add(new BasicNameValuePair("method", "lesson_history"));
		params.add(new BasicNameValuePair("condition", condition));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}

		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("URL Lesson History", urls);
		String jsonStr = jsonParser.getJSONFromUrl(urls);
		return jsonStr;
	}
	public String getPaidPayment(String unm, String pass, String values) {
		String urls="";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", payment_class));
		params.add(new BasicNameValuePair("method", method_paid));
		params.add(new BasicNameValuePair("condition", values));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}

		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("URL Paid Payment", urls);
		String jsonStr = jsonParser.getJSONFromUrl(urls);
		return jsonStr;
	}
	public String getPayablePayment(String unm, String pass, String values) {
		String urls="";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", payment_class));
		params.add(new BasicNameValuePair("method", "payment_data"));
		params.add(new BasicNameValuePair("condition", values));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}

		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("URL Payable Payment", urls);
		String jsonStr = jsonParser.getJSONFromUrl(urls);
		return jsonStr;
	}

	public String getPaymentStatistic(String unm, String pass, String values) {
		String urls="";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", payment_class));
		params.add(new BasicNameValuePair("method","payment_statistics"));
		params.add(new BasicNameValuePair("condition", values));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?"))
			URL += "?";

		String paramString = URLEncodedUtils.format(params, "utf-8");

		urls = URL +paramString;
		Log.e("Payment Url", urls);
		String jsonStr = jsonParser.getJSONFromUrl(urls);
		return jsonStr;
	}
	public String sendPayment(String unm, String pass, String sendPayjson) {
		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", "payment"));
		params.add(new BasicNameValuePair("method", "pay_installment"));
		params.add(new BasicNameValuePair("json", sendPayjson));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("Payment Insert URL", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}
	public String updatePayment(String unm, String pass, String payUpdatejson) {
		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", "payment"));
		params.add(new BasicNameValuePair("method", "pay_installment_update"));
		params.add(new BasicNameValuePair("json", payUpdatejson));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("Payment Update URL", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}
	public String sendDeleteMsg(String unm, String pass, String deleteJson) {

		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", "update"));
		params.add(new BasicNameValuePair("method", "update_data"));
		params.add(new BasicNameValuePair("json", deleteJson));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("send Delete Message URl", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}
	public String getLatLong(String std_loc) {
		String map_url = "http://maps.googleapis.com/maps/api/geocode/json?";
		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("address", std_loc));
		params.add(new BasicNameValuePair("sensor", "true_or_false"));

		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = map_url+paramString;
		Log.e("Lat Long URL", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}
	public String sendReadStatus(String unm, String pass, String readJson) {
		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", "update"));
		params.add(new BasicNameValuePair("method", "update_data"));
		params.add(new BasicNameValuePair("json", readJson));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("send Read Status URl", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}
	public String getProfile(String unm, String pass) {
		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", "select"));
		params.add(new BasicNameValuePair("method", "profile"));
		params.add(new BasicNameValuePair("table_name", "instructor_master"));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("Profile URl", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}


	public String sendCancel(String unm, String pass, String cancelJson) {
		String urls = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("class", "schedule"));
		params.add(new BasicNameValuePair("method", "no_show_status"));
		params.add(new BasicNameValuePair("json", cancelJson));
		params.add(new BasicNameValuePair("username", unm));
		params.add(new BasicNameValuePair("password", pass));

		if(!URL.endsWith("?")){
			URL += "?";
		}
		String paramString = URLEncodedUtils.format(params, "utf-8");
		urls = URL +paramString;
		Log.e("Cancel Lesson URl", urls);
		String json = jsonParser.getJSONFromUrl(urls);
		return json;
	}

	public void showSettingsAlert(){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

		// Setting Dialog Title
		alertDialog.setTitle("GPS is settings");

		// Setting Dialog Message
		alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

		// On pressing Settings button
		alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int which) {
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				context.startActivity(intent);
			}
		});

		// on pressing cancel button
		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}*/


}