package net.thequester.simulator;

import net.thequester.model.Node;
import net.thequester.model.Quest;
import net.thequester.model.QuestLocation;
import net.thequester.processor.IQuestProcessor;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author tdubravcevic
 */
public class QuestTest {

	private IQuestProcessor processor;
	private Map<QuestLocation, Node> expectedPath;
	private Quest quest;

	//CONSTRUCT PROCESSOR FROM QUEST

	@Test
	public void runQuest(){

		for(QuestLocation location : expectedPath.keySet()){

			Node node = processor.processLocation(location);
			assertEquals(expectedPath.get(location), node);
		}
	}
}
