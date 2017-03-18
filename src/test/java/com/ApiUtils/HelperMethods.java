package com.ApiUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.Validate;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class HelperMethods {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void setAnsiGreen(String msg){
        System.out.println(ANSI_GREEN +  msg  + ANSI_RESET) ;
    }

    public static void setAnsiRed(String msg){
        System.out.println(ANSI_RED +  msg  + ANSI_RESET) ;
    }


    /**
    Verify the http response status returned. Check Status Code is 200?
    We can use Rest Assured library's response's getStatusCode method
    */
    public static void checkStatusIs200 (Response res) {
        assertEquals("Status Check Failed!", 200, res.getStatusCode());
    }

    public static void checkStatusIs201 (Response res) {
        assertEquals("Status Check Failed!", 201, res.getStatusCode());
    }

    /**
    Get Video Ids (For example 1)
    We can use get method of Rest Assured library's JsonPath Class's get method
    PArt of a response is shown below:
    "items": [{
		"id": 519377522,
		....
	We can get all id's with this code --> "jp.get("items.id");" this will return
	all id's under "items" tag.
    */
    public static ArrayList getVideoIdList (JsonPath jp) {
        ArrayList videoIdList = jp.get("items.id");
        return videoIdList;
    }

    /**
    Get Related Video Ids (For example 2)
    Structure of response is shown below:
    items:
    	"related": [{
			"id": 519148754,
			....
	In order to get all id's under related tag,
    We can use JsonPath's get method like "jp.get("items.related.id");"
    It will give us all id's under related tag.
    */
    public static ArrayList getRelatedVideoIdList (JsonPath jp) {
        //jp.get method returns all ids
        ArrayList relatedVideoList = jp.get("items.related.id");
        /*
        Result of relatedVideosList: [[519148754, 519115214, 519235328, 519235341]]
        I have to convert above result in this format: [519148754, 519115214, 519235328, 519235341]
        In order to split first element of "relatedVideosList" and assign it to a new ArrayList (as splittedRelatedVideoList)
        I did below operation.
        */
        ArrayList splittedRelatedVideoList = (ArrayList) relatedVideoList.get(0);
        return splittedRelatedVideoList;
    }

    //Merge videoIdList and relatedVideoIdList as mergedVideoList
    public  static ArrayList mergeLists (ArrayList videoList, ArrayList relatedVideoList){
        ArrayList mergedVideoList = new ArrayList(videoList);
        mergedVideoList.addAll(relatedVideoList);
        return mergedVideoList;
    }

    //Find Duplicate Videos
    public static boolean findDuplicateVideos (List<Integer> videoIdList) {
        for (int i=0; i< videoIdList.size(); i++) {
            if(Collections.frequency(videoIdList, videoIdList.get(i)) > 1){
                System.out.println("This video id is duplicated: " + videoIdList.get(i));
                return false;
            }
        }
        return true;
    }

    public static void checkResponseTime (Response resp, String msg ) {
         Long time = resp.then().extract().time();
         System.out.println("SmartResponse Time of  "+ msg + time + " ms");
    }

    public static void printResponse( Response resp){
        System.out.println(resp.asString());
    }

    public static class CloseIdleConnectionConfig {
        private final long idleTime;
        private final TimeUnit timeUnit;

        /**
         * Close connections that have idled for the amount of time specified in this config.
         *
         * @param idleTime The idle time of connections to be closed
         * @param timeUnit The time unit to for <code>idleTime</code>
         */
        public CloseIdleConnectionConfig(long idleTime, TimeUnit timeUnit) {
            if (idleTime < 0) {
                throw new IllegalArgumentException("Idle time cannot be less than 0.");
            }
            Validate.notNull(timeUnit, "Timeunit cannot be null");
            this.idleTime = idleTime;
            this.timeUnit = timeUnit;
        }

        public long getIdleTime() {
            return idleTime;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }
    }

}