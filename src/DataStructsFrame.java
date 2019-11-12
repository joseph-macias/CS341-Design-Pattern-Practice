import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//test
public class DataStructsFrame extends JFrame {
	// CREATE BOOLEAN TO ONLY ADD THE LIST ONCE
	private static boolean ADDED = false;

	public DataStructsFrame(String title, int[] numbers1, int[] numbers2) {
		super(title);

		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		// CREATE TEXT BOX TO ADD EXTRA VALUES
		final ListPanel inputList = new ListPanel("Enter your own values(Seperate by commas):");
		inputList.setDiameter(75);
		JTextField valAText = new JTextField();
		JLabel valALable = new JLabel();
		valALable.setText("ValA:");
		JTextField valBText = new JTextField();
		JLabel valBLable = new JLabel();
		valBLable.setText("ValB:");

		final ArrayList<ListItem> list1 = arrayToList(numbers1, numbers2);
		final ListPanel unorderedList = new ListPanel("Unordered List");
		unorderedList.setDiameter(75);
		unorderedList.addItems(list1);

		final ListPanel orderedList1 = new ListPanel("Ordered List Descending By First Value");
		orderedList1.setDiameter(100);

		final ListPanel orderedList2 = new ListPanel("Ordered List Ascending By Second Value");
		orderedList2.setDiameter(100);

		JButton sortButton = new JButton("Sort List");
		sortButton.setSize(30, 10);
		sortButton.setAlignmentX(CENTER_ALIGNMENT);

		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// CHECK IF LIST HAS ALREADY BEEN ADDED
				if (ADDED == false) {
					// READ THE TEXT FROM THE TEXTFIELD
					String AText = valAText.getText();
					String BText = valBText.getText();
					// MAKE SURE THERE IS INPUT
					if (AText.isEmpty() || BText.isEmpty()) {
						valAText.setText("Error: valA or valB Text Field is empty. ");
						valBText.setText("Error: valA or valB Text Field is empty. ");
						// COMPUTE ORIGINAL LIST IF NO USER INPUT
						computeOriginal(orderedList1, orderedList2, list1, panel);
					}
					// COMPUTE NEW LIST
					else {
						// STORE VALUES IN AN ARRAY
						int[] newAValues = readAText(AText);
						int[] newBValues = readBText(BText);

						// MAKE SURE THE ARRAYS ARE THE SAME LENGTH
						if (newAValues.length != newBValues.length) {
							valAText.setText("Error: you must enter the same amount of values for valA and valB");
							valBText.setText("Error: you must enter the same amount of values for valA and valB");
							computeOriginal(orderedList1, orderedList2, list1, panel);
						} else {
							// ADD ON TO THE CURRENT PAIRS
							int[] newNumbers1 = addTheValues(numbers1, newAValues);
							int[] newNumbers2 = addTheValues(numbers2, newBValues);
							// CREATE A NEW LIST WITH THE NEW VALUES
							final ArrayList<ListItem> newList = arrayToList(newNumbers1, newNumbers2);
							orderedList1.addItems(sortListDescending(newList));
							orderedList2.addItems(sortListAscending(newList));
							panel.add(orderedList1);
							panel.add(orderedList2);
						}
					}
					ADDED = true;
					pack();
				}
			}
		});
		
		//ADD ELEMENTS TO PANEL
		panel.add(unorderedList);
		panel.add(inputList);
		panel.add(valALable);
		panel.add(valAText);
		panel.add(valBLable);
		panel.add(valBText);
		panel.add(sortButton);
		add(panel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private ArrayList<ListItem> arrayToList(int[] numbers1, int[] numbers2) {
		ArrayList<ListItem> list = new ArrayList<ListItem>();

		for (int i = 0; i < numbers1.length; i++) {
			ListItem item = new ListItem(numbers1[i], numbers2[i]);
			list.add(item);
		}

		return list;
	}

	private ArrayList<ListItem> sortListDescending(ArrayList<ListItem> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(i).getValA() < list.get(j).getValA()) {
					ListItem tmp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, tmp);
				}

			}

		}
		return list;
	}

	private ArrayList<ListItem> sortListAscending(ArrayList<ListItem> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(i).getValB() > list.get(j).getValB()) {
					ListItem tmp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, tmp);
				}

			}

		}
		return list;
	}

	private int[] readAText(String AText) {
		// SPLIT STRING BY COMMAS
		String[] aArray = AText.split(",");
		// STORE INTEGER VALUES
		int[] aValues = new int[aArray.length];
		for (int i = 0; i < aArray.length; i++) {
			aValues[i] = Integer.parseInt(aArray[i]);
		}
		return aValues;
	}

	private int[] readBText(String BText) {
		// SPLIT STRING BY COMMAS
		String[] bArray = BText.split(",");
		// STORE INTEGER VALUES
		int[] bValues = new int[bArray.length];
		for (int i = 0; i < bArray.length; i++) {
			bValues[i] = Integer.parseInt(bArray[i]);
		}
		return bValues;
	}

	private int[] addTheValues(int[] n, int[] a) {
		// merge the two arrays
		int length = n.length + a.length;
		int[] arr = new int[length];
		for (int i = 0; i < n.length; i++) {
			arr[i] = n[i];
			// System.out.println(arr[i]);
		}
		for (int j = n.length; j < length; j++) {
			arr[j] = a[j - n.length];
			// System.out.println(arr[j]);
		}
		return arr;
	}

	private void computeOriginal(ListPanel orderedList1, ListPanel orderedList2, ArrayList<ListItem> list1,
			JPanel panel) {
		orderedList1.addItems(sortListDescending(list1));
		orderedList2.addItems(sortListAscending(list1));
		panel.add(orderedList1);
		panel.add(orderedList2);
	}
}
