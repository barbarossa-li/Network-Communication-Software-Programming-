package ch4;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


public class InputStreamFile
{
    public static void main(String[] args)
    {
        InputStreamFile app = new InputStreamFile();
        app.fileToInputStream();
        app.inputStreamToFile();
    }


    public void fileToInputStream()
    {
        InputStream inputStream = null;
        BufferedReader br = null;
        try
        {
            String path = System.getProperty("user.dir") + "/bin/file.xml";
            inputStream = new FileInputStream(path);
            br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            System.out.println(sb.toString());
            System.out.println("\nDone!");
s
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (inputStream != null)
            {
                try
                {
                    inputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


    public void inputStreamToFile()
    {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try
        {
            String userDir = System.getProperty("user.dir");

            String path = userDir + "/bin/file.xml";
            inputStream = new FileInputStream(path);

            String newPath = userDir + "/bin/file-new.xml";
            File file = new File(newPath);
            outputStream = new FileOutputStream(file);

            int bytesWritten = 0;
            int byteCount = 0;

            byte[] bytes = new byte[1024];

            while ((byteCount = inputStream.read(bytes)) != -1)
            {
                outputStream.write(bytes, bytesWritten, byteCount);
                bytesWritten += byteCount;
            }

            System.out.println("Done!");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (inputStream != null)
            {
                try
                {
                    inputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (outputStream != null)
            {
                try
                {
                    outputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        }
    }

}
