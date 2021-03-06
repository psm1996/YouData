package com.mycompany.mavenproject2;
import com.google.api.client.http.HttpRequest;
import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import static com.mycompany.mavenproject2.Search.commentStream;
import static com.mycompany.mavenproject2.Search.commentsDataset;
import static com.mycompany.mavenproject2.Search.datasetfile;
import static com.mycompany.mavenproject2.Search.videofile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author soumy
 */
public class SearchPage extends javax.swing.JFrame {

    /**
     * Creates new form SearchPage
     */
    private static YouTube youtube;
    private static final long NUMBER_OF_VIDEOS_RETURNED = 10;
    private static String apiKey = "AIzaSyAvqnZR83EzZaIAXGPFLXUIUyAz5DX1FL4";
    public static String videofile = "C:\\Users\\soumy\\Desktop\\ran.txt";
    public static String datasetfile = "C:\\Users\\soumy\\Desktop\\VideoDataset.txt";
    public static String commentsDataset = "C:\\Users\\soumy\\Desktop\\CommentsDataset.txt";
    public static FileOutputStream commentStream;
    public static String mainPath = "C:\\Users\\soumy\\AppData\\Local\\Programs\\Python\\Python36\\python";

    public SearchPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jMenu1 = new javax.swing.JMenu();
        videoText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        videoText.setText("Search for vidoes here");

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 18)); // NOI18N
        jLabel1.setText("Please search for the keyword here");

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(videoText, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(videoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(591, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        try {
            String queryTerm = this.videoText.getText();
            Properties properties = new Properties();
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, (HttpRequest request) -> {
            }).setApplicationName("youtube-cmdline-search-sample").build();
            YouTube.Search.List search;
            try {
                search = youtube.search().list("id,snippet");
                search.setKey(apiKey);
                search.setQ(queryTerm);
                search.setType("video");
                search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
                search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

                SearchListResponse searchResponse = search.execute();
                System.out.println(queryTerm);
                List<SearchResult> searchResultList = searchResponse.getItems();
                System.err.println("No. of results= " + searchResultList.size());
                if (searchResultList != null) {
                    prettyPrint(searchResultList.iterator(), queryTerm);
                    func();
                }
            } catch (IOException ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please write the Video that youu want to search", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public void func() throws IOException{
        System.out.println("");
      String x =  "C:\\Users\\soumy\\Desktop\\actual_sentiment.py" ;
      String[] total = {mainPath,x};
      ProcessBuilder pbuild = new ProcessBuilder(total);
      pbuild.redirectErrorStream(true);
      
       Process proc = pbuild.start();
        BufferedReader brm = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                       String cOutput;
                        while((cOutput = brm.readLine()) != null)
                        { System.out.println(cOutput);
                        }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField videoText;
    // End of variables declaration//GEN-END:variables

    private static void prettyPrint(Iterator<SearchResult> iterator, String query) {
        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        String fileContent = "\n============================================================="
                + "\n   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\"."
                + "=============================================================\n\n";

        try (FileOutputStream outputStream = new FileOutputStream(videofile)) {
            byte[] strToBytes = fileContent.getBytes();
            outputStream.write(strToBytes);

            FileOutputStream datasetOutputStream = new FileOutputStream(datasetfile);
            String row = "VideoId,viewCount,likeCount,dislikeCount,favoriteCount,commentCount\n";
            byte[] stringToBytes = row.getBytes();
            datasetOutputStream.write(stringToBytes);

            commentStream = new FileOutputStream(commentsDataset);
            String comRow = "videoId" + "\t" + "Comment" + "\t" + "likeCount" + "\t" + "publishedAt" + "\t" + "totalReplyCount\n";
            commentStream.write(comRow.getBytes());

            FileWriter fw = new FileWriter(datasetfile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            if (!iterator.hasNext()) {
                System.out.println(" There aren't any results for your query.");
            }

            while (iterator.hasNext()) {
                SearchResult singleVideo = iterator.next();
                ResourceId rId = singleVideo.getId();
                // Confirm that the result represents a video. Otherwise, the
                // item will not contain a video ID.
                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
                    System.out.println(" Video Id" + rId.getVideoId());
                    String temp = (" Video Id" + rId.getVideoId());
                    byte[] StrToBytes = temp.getBytes();
                    outputStream.write(StrToBytes);
                    System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                    temp = " Title: " + singleVideo.getSnippet().getTitle();
                    StrToBytes = temp.getBytes();
                    outputStream.write(StrToBytes);
                    System.out.println(" Thumbnail: " + thumbnail.getUrl());
                    System.out.println("\n-------------------------------------------------------------\n");
                    temp = " Thumbnail: " + thumbnail.getUrl() + "\n-------------------------------------------------------------\n";
                    StrToBytes = temp.getBytes();
                    outputStream.write(StrToBytes);

                    //creating dataset
                    String videourl = "https://www.googleapis.com/youtube/v3/videos?key=" + apiKey + "&part=statistics&id="
                            + rId.getVideoId();
                    URL obj = new URL(videourl);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputline;
                    StringBuffer response = new StringBuffer();
                    while ((inputline = in.readLine()) != null) {
                        response.append(inputline);
                    }
                    in.close();

                    JSONObject jsonobj = new JSONObject(response.toString());
                    JSONObject jobj2 = jsonobj.getJSONArray("items").getJSONObject(0).getJSONObject("statistics");
//                System.out.println(jobj2);
                    row = rId.getVideoId() + "," + jobj2.getString("viewCount") + "," + jobj2.getString("likeCount")
                            + "," + jobj2.getString("dislikeCount") + ","
                            + jobj2.getString("favoriteCount") + ","
                            + jobj2.getString("commentCount") + "\n";
                    stringToBytes = row.getBytes();
                    datasetOutputStream.write(stringToBytes);
//                out.println(row);
//                Files.write(Paths.get(datasetfile),row.getBytes(),StandardOpenOption.APPEND);
                    commentParsing(rId.getVideoId());
                }

            }

        } catch (Exception e) {
            System.out.print(e + " error");
        }

    }

    private static void commentParsing(String videoId) {
        try {
//            FileOutputStream commentStream=new FileOutputStream(commentsDataset);

            String row;
            String comUrl = "https://www.googleapis.com/youtube/v3/commentThreads?key=AIzaSyAvqnZR83EzZaIAXGPFLXUIUyAz5DX1FL4&textFormat=plainText&part=snippet&videoId=" + videoId + "&maxResults=50";
            URL obj = new URL(comUrl);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputline;
            StringBuffer response = new StringBuffer();
            while ((inputline = in.readLine()) != null) {
                response.append(inputline);
            }
            in.close();

            JSONObject jsonobj = new JSONObject(response.toString());
            JSONArray array = jsonobj.getJSONArray("items");
//                System.out.println(array);
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject obj2 = array.getJSONObject(i).getJSONObject("snippet");
                    JSONObject obj21 = obj2.getJSONObject("topLevelComment").getJSONObject("snippet");
                    String x = obj21.get("textOriginal") + "";
                    x = x.replace('\n', ' ');
                    x = x.replace(',', ' ');

                    row = obj2.get("videoId") + "\t" + x + "\t"
                            + obj21.get("likeCount") + "\t" + obj21.get("publishedAt") + "\t"
                            + obj2.get("totalReplyCount") + "\n";
//                      System.out.println(row);
                    commentStream.write(row.getBytes());
//                      System.out.println(obj2);
                } catch (Exception e) {

                }

            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
