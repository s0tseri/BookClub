import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.*;

/**
 * Created by Haru on 2/19/2015.
 */
public class frmMain {

    //Initialize Form Components
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel tabHome;
    private JPanel tabMembers;
    private JPanel tabPastBooks;
    private JPanel pnlNextMeeting;
    private JLabel lblBookTitle;
    private JButton btnAddBook;
    private JLabel lblDoneReading;
    private JButton btnReviewBook;
    private JLabel lblAuthorName;
    private JLabel lblLocation;
    private JLabel lblDateTime;
    private JLabel lblMeetingNotSet;
    private JButton btnSetMeeting;
    private JLabel lblNoBookSelected;
    private JList lboMembers;
    private JButton btnEmailMembers;
    private JButton btnAddMember;
    private JButton btnEditMember;
    private JPanel pnlNoBook;
    private JPanel pnlNoMeeting;
    private JList lboBookSuggestions;
    private JPanel tabBookSuggestions;
    private JList lboPastBooks;
    private JButton btnReviewPastBook;
    private JButton btnViewBook;
    private JPanel pnlMeetingInfo;
    private JPanel pnlBookInfo;


    //Create test book object
    private Book b = new Book("The Hobbit", "Tolkein");
    private Book assignedReading;

    //Create test meeting object
    private Date meetingDate = new Date();
    private Meeting m = new Meeting(meetingDate, "MCTC T-Building, Room T-3050");
    private Meeting nextMeeting;

    public frmMain() {
        assignedReading = b;
        nextMeeting = m;

        /** HOME TAB **/

        UpdateHomeTab();

        //Set Meeting
        btnSetMeeting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSetMeetingDialog();
            }
        });

        //Add Book
        btnAddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddBookDialog();
            }
        });

        //Review Book
        btnReviewBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBookReviewDialog();
            }
        });

        /** MEMBERS TAB **/

        /** PAST BOOKS TAB **/
        btnReviewPastBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lboPastBooks.isSelectionEmpty()) {
                    Validator.messageBox("Please select a book to review", "Select Book");
                } else {
                    openBookReviewDialog();
                }
            }
        });

        /** BOOK SUGGESTIONS TAB **/
    }

    private void UpdateHomeTab() {
        //TODO
        //Get book info
        if (assignedReading == null) {
            pnlBookInfo.setVisible(false);
            lblNoBookSelected.setVisible(true);
            btnAddBook.setText("Add Book");
        } else {
            lblNoBookSelected.setVisible(false);
            pnlBookInfo.setVisible(true);
            btnAddBook.setText("Edit Book Info");
            lblBookTitle.setText(assignedReading.getTitle());
            lblAuthorName.setText(assignedReading.getAuthor());
        }

        //Get meeting info
        if (nextMeeting == null) {
            pnlMeetingInfo.setVisible(false);
            lblMeetingNotSet.setVisible(true);
            btnSetMeeting.setText("Set Meeting");
        } else {
            pnlMeetingInfo.setVisible(true);
            lblMeetingNotSet.setVisible(false);
            btnSetMeeting.setText("Edit Meeting Info");
            DateFormat df = new SimpleDateFormat("EEEE, MMMM dd, yyyy 'at' HH:mm a");
            lblLocation.setText(nextMeeting.getLocation());
            lblDateTime.setText(df.format(nextMeeting.getDateTime()));
        }
    }

    private void openSetMeetingDialog() {
        //Initialize and open set meeting dialog
        dlgSetMeeting setMeetingDialog = new dlgSetMeeting();
        setMeetingDialog.setTitle("Set Meeting");

        //Set dimensions
        Dimension dimensions = new Dimension(325, 175);
        setMeetingDialog.setSize(dimensions);
        setMeetingDialog.setResizable(false);

        //Center form on screen
        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();   //Get screen size

        int screenCenterX = screenDimensions.width / 2;
        int screenCenterY = screenDimensions.height / 2;

        int frameCenterX = setMeetingDialog.getSize().width / 2;
        int frameCenterY = setMeetingDialog.getSize().height / 2;

        setMeetingDialog.setLocation(screenCenterX - frameCenterX, screenCenterY - frameCenterY);

        setMeetingDialog.setVisible(true);
    }

    private void openAddBookDialog() {
        //Initialize and open add book dialog
        dlgAddBook addBookDialog = new dlgAddBook();
        addBookDialog.setTitle("Add Book");

        //Set dimensions
        Dimension dimensions = new Dimension(300, 175);
        addBookDialog.setSize(dimensions);
        addBookDialog.setResizable(false);

        //Center form on screen
        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();   //Get screen size

        int screenCenterX = screenDimensions.width / 2;
        int screenCenterY = screenDimensions.height / 2;

        int frameCenterX = addBookDialog.getSize().width / 2;
        int frameCenterY = addBookDialog.getSize().height / 2;

        addBookDialog.setLocation(screenCenterX - frameCenterX, screenCenterY - frameCenterY);

        addBookDialog.setVisible(true);
    }

    private void openBookReviewDialog() {
        //Initialize and open review book dialog
        dlgReviewBook reviewBookDialog = new dlgReviewBook();
        reviewBookDialog.setTitle("Review Book");

        //Set dimensions
        Dimension dimensions = new Dimension(300, 250);
        reviewBookDialog.setSize(dimensions);
        reviewBookDialog.setMinimumSize(dimensions);

        //Center form on screen
        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();   //Get screen size

        int screenCenterX = screenDimensions.width / 2;
        int screenCenterY = screenDimensions.height / 2;

        int frameCenterX = reviewBookDialog.getSize().width / 2;
        int frameCenterY = reviewBookDialog.getSize().height / 2;

        reviewBookDialog.setLocation(screenCenterX - frameCenterX, screenCenterY - frameCenterY);

        reviewBookDialog.setVisible(true);
    }

    public static void main(String[] args) {
        //Initialize form
        JFrame frame = new JFrame("frmMain");
        frame.setContentPane(new frmMain().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle("Book Club");    //Form title

        //Set dimension properties
        Dimension dimensions = new Dimension(500, 400);
        frame.setSize(dimensions);
        frame.setMinimumSize(dimensions);

        //Center form on screen
        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();   //Get screen size

        int screenCenterX = screenDimensions.width / 2;
        int screenCenterY = screenDimensions.height / 2;

        int frameCenterX = frame.getSize().width / 2;
        int frameCenterY = frame.getSize().height / 2;

        frame.setLocation(screenCenterX - frameCenterX, screenCenterY - frameCenterY);

        //Show form
        frame.setVisible(true);

//        //Connect to DB
//        DB db = new DB();
//
//        db.Connect();
//
//        args= new String[4];
//        //DataSource.DataSource(args);
    }
}