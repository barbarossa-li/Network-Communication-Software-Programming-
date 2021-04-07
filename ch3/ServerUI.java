package ch3;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * 功能：KnockKnock游戏的Server设计
 * 设计：董相志，版权所有，2016--2018，upsunny2008@163.com
 */
public class ServerUI extends javax.swing.JFrame {
    private ExecutorService fixedPool; //线程池
    private ServerSocket listenSocket; //侦听套接字
    private Socket toClientSocket; //与客户机对话的套接字
    public static int clientCounts=0; //客户机数量
    /**
     * Creates new form KnockKnockServer
     */
    public ServerUI() {
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

        topPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtHostName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtHostPort = new javax.swing.JTextField();
        btnStart = new javax.swing.JButton();
        midPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("KnockKnockServer服务器--设计：董相志，版权所有，2016--2018");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        topPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "启动服务器面板", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("宋体", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N
        jLabel1.setText("服务器主机名：");

        txtHostName.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N
        txtHostName.setText("localhost");

        jLabel2.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N
        jLabel2.setText("服务器端口：");

        txtHostPort.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N
        txtHostPort.setText("5000");

        btnStart.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N
        btnStart.setText("启动服务器");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHostName, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtHostPort, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStart)
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtHostName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtHostPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStart))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(topPanel, java.awt.BorderLayout.PAGE_START);

        midPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "服务器消息面板，服务器扮演“门外人”角色", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("宋体", 1, 18))); // NOI18N
        midPanel.setLayout(new java.awt.BorderLayout());

        txtArea.setEditable(false);
        txtArea.setBackground(new java.awt.Color(255, 204, 255));
        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        midPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(midPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //启动服务器
    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        try {
            btnStart.setEnabled(false);
            txtHostName.setEnabled(false);
            txtHostPort.setEnabled(false);
             //获取启动参数
            String hostName=txtHostName.getText();
            int hostPort=Integer.parseInt(txtHostPort.getText());
            //构建套接字格式的地址
            SocketAddress serverAddr=new InetSocketAddress(InetAddress.getByName(hostName),hostPort);
            listenSocket=new ServerSocket(); //创建侦听套接字
            listenSocket.bind(serverAddr); //绑定到工作地址
            int processors=Runtime.getRuntime().availableProcessors();//CPU数
            fixedPool=Executors.newFixedThreadPool(processors*2);//创建固定大小线程池
            long currentId=Thread.currentThread().getId();
            txtArea.append("服务器CPU数："+processors+",固定线程池大小："+processors*2+",当前侦听线程ID："+currentId+"，服务器正等待客户机连接...\n");                    
        } catch (IOException ex) { }
 
        new Thread(new Runnable() {
            public void run() {
              try {            
                while (true) { //处理所有客户机连接
                    toClientSocket=listenSocket.accept();//如果无连接，则阻塞，否则接受连接并创建新的会话套接字
                    clientCounts++;
                    txtArea.append(toClientSocket.getRemoteSocketAddress()+" 客户机编号："+clientCounts+" 连接到服务器，会话开始...\n");
                    //客户会话线程为SwingWorker类型的后台工作线程
                    Thread clientThread=new ClientThread(toClientSocket,clientCounts); //创建客户线程
                    fixedPool.execute(clientThread); //用线程池调度客户线程运行
                    //clientThread.start(); //这样做就是一客户一线程
                }//end while 
              } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
              }//end try catch            
           }//end run()
        }).start();         
    }//GEN-LAST:event_btnStartActionPerformed
    //关闭服务器之前
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       //关闭套接字和线程池
        try {
            if (toClientSocket!=null) toClientSocket.close();
            if (listenSocket!=null) listenSocket.close(); 
            if (fixedPool==null)  return;
            fixedPool.shutdown(); //线程池开始关闭
            if (!fixedPool.awaitTermination(60, TimeUnit.SECONDS)) {
                fixedPool.shutdownNow();
                if (!fixedPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    fixedPool.shutdownNow();
                }
            }            
        } catch (IOException | InterruptedException ex) { 
            fixedPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(ServerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel midPanel;
    private javax.swing.JPanel topPanel;
    public static javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtHostName;
    private javax.swing.JTextField txtHostPort;
    // End of variables declaration//GEN-END:variables
}
