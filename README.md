# AndroidHttpController
<br>
HttpPostController is a Java class that helps to manage Post Request in Android.
<br>
<br>
<h2>-CONSTRUCTOR:</h2>
  <h4>HttpPostController(Controller controller);</h4>
    In order to manage the class correctly you have to pass the controller of your application.<br><br>
<h2>-METHODS:</h2>
  <h4>1)sendHttpPostRequest(String url);</h4>
       This method perform a Http Post Request without parameters to the URL provided.<br><br>
  <h4>2)sendHttpPostRequest(String url,HashMap&lt;String,String&gt; values);</h4>
       This method perform a Http Post Request with parameters to the URL provided.<br>
       The first string of the HashMap it is the Key, the name which you have to refer to when you have to retrieve the value in your API.<br>  The second string is the actual value that you want to pass.
       
