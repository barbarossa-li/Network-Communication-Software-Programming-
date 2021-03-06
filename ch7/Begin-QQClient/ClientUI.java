package cn.edu.ldu;

import cn.edu.ldu.util.Message;
import cn.edu.ldu.util.Translate;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * ClientUI，客户机聊天界面类
 * @author  董相志，版权所有2016--2018，upsunny2008@163.com
 */
public class ClientUI extends javax.swing.JFrame {
    private DatagramSocket clientSocket; //客户机套接字
    private Message msg; //消息对象
    private byte[] data=new byte[8096]; //8K字节数组
    /**
     * Creates new form ClientUI
     */
    public ClientUI() {
        initComponents();
        //设置窗体位置到屏幕中央
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width)/2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height)/2;
        this.setLocation(x, y);
        filePanel.setVisible(false);
    }
    /**
     * 构造函数
     * @param socket 客户机会话套接字
     * @param msg 登录消息对象
     */
    public ClientUI(DatagramSocket socket,Message msg) {
        this(); //调用无参数构造函数，初始化界面
        clientSocket=socket; //初始化会话套接字
        this.msg=msg; //登录消息
        //创建客户机消息接收和处理线程
        Thread recvThread=new ReceiveMessage(clientSocket,this);
        recvThread.start();//启动消息线程    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileMenu = new javax.swing.JPopupMenu();
        uploadFile = new javax.swing.JMenuItem();
        downloadFile = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtInput = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<>();
        btnSend = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btnPhone = new javax.swing.JButton();
        btnVideo = new javax.swing.JButton();
        btnFile = new javax.swing.JButton();
        btnRemote = new javax.swing.JButton();
        filePanel = new javax.swing.JPanel();
        fileLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        progressLabel = new javax.swing.JLabel();

        uploadFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn/edu/ldu/images/upload.png"))); // NOI18N
        uploadFile.setText("上传文件");
        uploadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadFileActionPerformed(evt);
            }
        });
        fileMenu.add(uploadFile);

        downloadFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn/edu/ldu/images/download.png"))); // NOI18N
        downloadFile.setText("下载文件");
        fileMenu.add(downloadFile);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("会话消息窗口"));

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("发言窗口"));

        txtInput.setColumns(20);
        txtInput.setRows(5);
        jScrollPane2.setViewportView(txtInput);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("在线用户"));

        jScrollPane3.setViewportView(userList);

        btnSend.setText("发送");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnPhone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn/edu/ldu/images/phone.png"))); // NOI18N
        btnPhone.setText("语音聊天");
        btnPhone.setFocusable(false);
        btnPhone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPhone.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnPhone);

        btnVideo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn/edu/ldu/images/video.png"))); // NOI18N
        btnVideo.setText("视频聊天");
        btnVideo.setFocusable(false);
        btnVideo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVideo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnVideo);

        btnFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn/edu/ldu/images/file.png"))); // NOI18N
        btnFile.setText("文件传输");
        btnFile.setFocusable(false);
        btnFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnFileMousePressed(evt);
            }
        });
        jToolBar1.add(btnFile);

        btnRemote.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn/edu/ldu/images/remote.png"))); // NOI18N
        btnRemote.setText("远程桌面");
        btnRemote.setFocusable(false);
        btnRemote.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRemote.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnRemote);

        fileLabel.setText("文件名：");

        progressBar.setStringPainted(true);

        javax.swing.GroupLayout filePanelLayout = new javax.swing.GroupLayout(filePanel);
        filePanel.setLayout(filePanelLayout);
        filePanelLayout.setHorizontalGroup(
            filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(filePanelLayout.createSequentialGroup()
                        .addComponent(fileLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(filePanelLayout.createSequentialGroup()
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(progressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        filePanelLayout.setVerticalGroup(
            filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filePanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(fileLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(progressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSend))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(btnSend))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
               try {
            msg.setText(txtInput.getText());//获取输入的文本
            msg.setType("M_MSG"); //普通会话消息
            data=Translate.ObjectToByte(msg);//消息对象序列化
            //构建发送报文
            DatagramPacket packet=new DatagramPacket(data,data.length,msg.getToAddr(),msg.getToPort());
            clientSocket.send(packet); //发送
            txtInput.setText(""); //清空输入框
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
        }       
    }//GEN-LAST:event_btnSendActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        try {
            msg.setType("M_QUIT"); //消息类型
            msg.setText(null);
            data=Translate.ObjectToByte(msg); //消息对象序列化
            //构建发送
            DatagramPacket packet=new DatagramPacket(data,data.length,msg.getToAddr(),msg.getToPort());       
            clientSocket.send(packet); //发送
        } catch (IOException ex) { }
        clientSocket.close(); //关闭套接字
    }//GEN-LAST:event_formWindowClosing

    private void btnFileMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFileMousePressed
        // TODO add your handling code here:
        fileMenu.show(btnFile, evt.getX()-35, evt.getY()+40);
    }//GEN-LAST:event_btnFileMousePressed

    private void uploadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadFileActionPerformed
        // TODO add your handling code here:
                //打开文件选择对话框
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setDialogTitle("选择上传文件");
        fileChooser.setApproveButtonText("选择");
        int choice=fileChooser.showOpenDialog(this); //显示对话框
        if (choice==JFileChooser.APPROVE_OPTION) { //点击选择按钮           
            File file=fileChooser.getSelectedFile();//获取文件对象
            //启动发送文件线程
            SwingWorker<List<String>,String> sender=new FileSender(file,msg,this);
            sender.addPropertyChangeListener(new PropertyChangeListener() {
         public  void propertyChange(PropertyChangeEvent evt) {
             if ("progress".equals(evt.getPropertyName())) {
                 progressBar.setValue((Integer)evt.getNewValue());
             }
         }
     });
         filePanel.setVisible(true);
         fileLabel.setText("文件："+file.getName());
         sender.execute(); 
         
        }
    }//GEN-LAST:event_uploadFileActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFile;
    private javax.swing.JButton btnPhone;
    private javax.swing.JButton btnRemote;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnVideo;
    private javax.swing.JMenuItem downloadFile;
    private javax.swing.JLabel fileLabel;
    private javax.swing.JPopupMenu fileMenu;
    public static javax.swing.JPanel filePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar jToolBar1;
    public static javax.swing.JProgressBar progressBar;
    public static javax.swing.JLabel progressLabel;
    public static javax.swing.JTextArea txtArea;
    private javax.swing.JTextArea txtInput;
    private javax.swing.JMenuItem uploadFile;
    public static javax.swing.JList<String> userList;
    // End of variables declaration//GEN-END:variables
}
