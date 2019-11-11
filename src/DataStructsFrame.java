import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//test
public class DataStructsFrame extends JFrame {
	// CREATE BOOLEAN TO ONLY ADD THE LIST ONCE
	private static boolean ADDED = false;

	public DataStructsFrame(String title, int[] numbers1, int[] numbers2) {
		super(title);

		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		ArrayList<ListItem> list1 = arrayToList(numbers1, numbers2);
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
					orderedList1.addItems(sortListDescending(list1));
					orderedList2.addItems(sortListAscending(list1));
					panel.add(orderedList1);
					panel.add(orderedList2);
					ADDED = true;
					pack();
				}
			}
		});

		panel.add(unorderedList);
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
}
