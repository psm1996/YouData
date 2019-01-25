/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.common.io.Files;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.*;

/**
 *
 * @author lokesh
 */
public class Search {
    private static YouTube youtube;
    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;
    private static String apiKey="AIzaSyAvqnZR83EzZaIAXGPFLXUIUyAz5DX1FL4";
    public static String videofile="/home/lokesh/Documents/ran.txt";
    public static String datasetfile="/home/lokesh/Documents/VideoDataset.txt";
    public static String commentsDataset="/home/lokesh/Documents/CommentsDataset.txt";
    public static FileOutputStream commentStream;
    
    
    public static void main(String[] args) throws IOException{
//        System.out.println("Working");
        Properties properties=new Properties();
        youtube=new YouTube.Builder(Auth.HTTP_TRANSPORT,Auth.JSON_FACTORY, (HttpRequest request) -> {
        }).setApplicationName("youtube-cmdline-search-sample").build();
        String queryTerm=getInputQuery();
        
        YouTube.Search.List search;
        try {
            search = youtube.search().list("id,snippet");
            search.setKey(apiKey);
            search.setQ(queryTerm);
            search.setType("video");
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            
            SearchListResponse searchResponse=search.execute();
            System.out.println(queryTerm);
            List<SearchResult> searchResultList=searchResponse.getItems();
            System.err.println("No. of results= "+searchResultList.size());
            if(searchResultList!=null){
                prettyPrint(searchResultList.iterator(), queryTerm);
            }
        } catch (IOException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
    }

    private static String getInputQuery() throws IOException{
        String inputQuery="";
        System.out.println("Please enter a search item");
        BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
        inputQuery=bReader.readLine();
        
        if(inputQuery.length()<1){
            inputQuery="Avengers end game";
        }
        return inputQuery;
    }

    private static void prettyPrint(Iterator<SearchResult> iterator, String query) {
        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");
        
        String fileContent = "\n============================================================="+
                "\n   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\"."
                +"=============================================================\n\n";
        
        try (FileOutputStream outputStream = new FileOutputStream(videofile)) {
            byte[] strToBytes = fileContent.getBytes();
            outputStream.write(strToBytes);
            
            FileOutputStream datasetOutputStream=new FileOutputStream(datasetfile);
            String row="VideoId,viewCount,likeCount,dislikeCount,favoriteCount,commentCount\n";
            byte[] stringToBytes=row.getBytes();
            datasetOutputStream.write(stringToBytes);
            
            commentStream=new FileOutputStream(commentsDataset);
            String comRow="videoId,textOriginal,likeCount,publishedAt,totalReplyCount\n";
            commentStream.write(comRow.getBytes());
            
            FileWriter fw=new FileWriter(datasetfile,true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter out=new PrintWriter(bw);
        
        if(!iterator.hasNext()){
            System.out.println(" There aren't any results for your query.");
        }
        
        while(iterator.hasNext()){
            SearchResult singleVideo=iterator.next();
            ResourceId rId=singleVideo.getId();
            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if(rId.getKind().equals("youtube#video")){
                Thumbnail thumbnail=singleVideo.getSnippet().getThumbnails().getDefault();
                System.out.println(" Video Id" + rId.getVideoId());
                String temp=(" Video Id" + rId.getVideoId());
                byte[] StrToBytes = temp.getBytes();
                outputStream.write(StrToBytes);
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                temp=" Title: " + singleVideo.getSnippet().getTitle();
                StrToBytes = temp.getBytes();
                outputStream.write(StrToBytes);
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
                temp=" Thumbnail: " + thumbnail.getUrl()+"\n-------------------------------------------------------------\n";
                StrToBytes = temp.getBytes();
                outputStream.write(StrToBytes);
                
                
                //creating dataset
                String videourl="https://www.googleapis.com/youtube/v3/videos?key="+apiKey+"&part=statistics&id="
                        +rId.getVideoId();
                URL obj=new URL(videourl);
                HttpURLConnection con=(HttpURLConnection)obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent","Mozilla/5.0");
                BufferedReader in=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputline;
                StringBuffer response=new StringBuffer();
                while((inputline=in.readLine())!=null){
                    response.append(inputline);
                }in.close();
                
                JSONObject jsonobj=new JSONObject(response.toString());
                JSONObject jobj2=jsonobj.getJSONArray("items").getJSONObject(0).getJSONObject("statistics");
//                System.out.println(jobj2);
                row=rId.getVideoId()+","+jobj2.getString("viewCount")+","+jobj2.getString("likeCount")+
                        ","+jobj2.getString("dislikeCount")+","
                        +jobj2.getString("favoriteCount")+","+
                        jobj2.getString("commentCount")+"\n";
                stringToBytes=row.getBytes();
                datasetOutputStream.write(stringToBytes);
//                out.println(row);
//                Files.write(Paths.get(datasetfile),row.getBytes(),StandardOpenOption.APPEND);
                commentParsing(rId.getVideoId());
            }
            
        }
        
        }catch(Exception e){
            System.out.print(e+" error");
        }

    }

    private static void commentParsing (String videoId) {
        try{
//            FileOutputStream commentStream=new FileOutputStream(commentsDataset);
            
            String row;
            String comUrl="https://www.googleapis.com/youtube/v3/commentThreads?key=AIzaSyAvqnZR83EzZaIAXGPFLXUIUyAz5DX1FL4&textFormat=plainText&part=snippet&videoId="+videoId+"&maxResults=50";
            URL obj=new URL(comUrl);
            HttpURLConnection con=(HttpURLConnection)obj.openConnection();
            con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent","Mozilla/5.0");
                BufferedReader in=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputline;
                StringBuffer response=new StringBuffer();
                while((inputline=in.readLine())!=null){
                    response.append(inputline);
                }in.close();
                
                JSONObject jsonobj=new JSONObject(response.toString());
                JSONArray array=jsonobj.getJSONArray("items");
//                System.out.println(array);
                for(int i=0;i<array.length();i++){
                    try{
                        JSONObject obj2=array.getJSONObject(i).getJSONObject("snippet");
                        JSONObject obj21=obj2.getJSONObject("topLevelComment").getJSONObject("snippet");
                        row=obj2.get("videoId")+","+obj21.get("textOriginal")+","+
                            obj21.get("likeCount")+","+obj21.get("publishedAt")+
                                    obj2.get("totalReplyCount")+"\n";
//                      System.out.println(row);
                        commentStream.write(row.getBytes());
//                      System.out.println(obj2);
                    }catch(Exception e){
                        
                    }
                    
                }
                
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
