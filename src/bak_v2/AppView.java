package bak_v2;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import viewPanels.SummaryPanel;
import viewPanels.FilePanel;
import viewPanels.NavBar;
import viewPanels.SettingsExtendPanel;
import viewPanels.SettingsPanel;
import viewPanels.SingleInputPanel;
import viewPanels.TrainingPanel;


public class AppView extends JFrame {
    
    private final Model v_neuModel;
    private final JPanel panelCards = new JPanel();
    
    private GroupLayout appViewLayout;
    
    private GroupLayout navLayout;

    private NavBar navBar;
    private SettingsPanel settingsPanel;
    private TrainingPanel trainingPanel;
    private SettingsExtendPanel settingExtendPanel;
    private FilePanel filePanel;
    private SummaryPanel endTrainigPanel;
    private SingleInputPanel singleInputPanel;
    
    public void printValues() {
        trainingPanel.errTrainLabel(String.valueOf(v_neuModel.getErrTrain()));
        trainingPanel.iterationLabel(String.valueOf(v_neuModel.getIteration()));
    }
    
    public void showFilePanel() {
        CardLayout cardLayout = (CardLayout)(panelCards.getLayout());
        navBar.unHighlightSummary();
        navBar.highlightData();
        cardLayout.show(panelCards, "fileType");
    }
    
    public void showSingleInputPanelFromLoad() {
        CardLayout cardLayout = (CardLayout)(panelCards.getLayout());
        navBar.unHighlightData();
        navBar.highlightSingleInput();
        cardLayout.show(panelCards, "singleInput");
    }
    
    public void showSingleInputPanelFromSummary() {
        CardLayout cardLayout = (CardLayout)(panelCards.getLayout());
        navBar.unHighlightSummary();
        navBar.highlightSingleInput();
        cardLayout.show(panelCards, "singleInput");
    }
    
    public void showEndTrainingPanel() {
        CardLayout cardLayout = (CardLayout)(panelCards.getLayout());
        navBar.unHighlightTraining();
        navBar.highlightSummary();
        cardLayout.show(panelCards, "end");
    }
    
    public void showSettingsPanel() {
        CardLayout cardLayout = (CardLayout)(panelCards.getLayout());
        navBar.unHighlightData();
        navBar.highlightSettings();
        cardLayout.show(panelCards, "settings");          
    }   
    
    public void showExtendSettingsPanel() {
        CardLayout cardLayout = (CardLayout)(panelCards.getLayout());
        cardLayout.show(panelCards, "settingsExtend");  
    }
    
    public void showTrainPanel() {
        CardLayout cardLayout = (CardLayout)(panelCards.getLayout());
        navBar.unHighlightSettings();
        navBar.highlightTraining();
        cardLayout.show(panelCards, "training");        
    }
    
    private void initLayout() {
        appViewLayout = new javax.swing.GroupLayout(getContentPane());
        
        getContentPane().setLayout(appViewLayout);
        appViewLayout.setHorizontalGroup(
            appViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appViewLayout.createSequentialGroup()
                .addComponent(navBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        appViewLayout.setVerticalGroup(
            appViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCards, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );          
    }
    
    public AppView(Model neuModel, NavBar navBar, FilePanel filePanel, SettingsPanel settingsPanel, SettingsExtendPanel settingExtendPanel, TrainingPanel trainingPanel, SummaryPanel endTrainigPanel, SingleInputPanel singleInputPanel) {
        super("Bak_V2");
        this.navBar = navBar;
        this.filePanel = filePanel;
        this.settingsPanel = settingsPanel;
        this.trainingPanel = trainingPanel;
        this.settingExtendPanel = settingExtendPanel;
        this.endTrainigPanel = endTrainigPanel;
        this.singleInputPanel = singleInputPanel;
        
        this.v_neuModel = neuModel;
        this.trainingPanel = new TrainingPanel(neuModel, "Chyba sieťe", "Iterácie", "MSE", v_neuModel.getGraphData(), v_neuModel.getSuccessData());
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1000, 800));
      
        this.panelCards.setLayout(new CardLayout());
        
        this.panelCards.add("fileType", filePanel);
        this.panelCards.add("settings", settingsPanel);
        this.panelCards.add("settingsExtend", settingExtendPanel);
        this.panelCards.add("training", trainingPanel);
        this.panelCards.add("end", endTrainigPanel);
        this.panelCards.add("singleInput", singleInputPanel);

        this.initLayout();
        navBar.highlightData();
        this.setLayout(appViewLayout);
        
        this.setVisible(true);
    }
    
}
