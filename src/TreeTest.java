import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TreeTest {
	private Tree<Integer> tree;
	private Random random;
	
	@Before
	public void setUp() throws Exception {
		tree = new Tree<Integer>();
		random = new Random();
	}
	
	@Test
	public void testAdd() throws Exception {
		assertTrue(tree.add(11));
		assertTrue(tree.add(9));
		assertFalse(tree.add(9));
		assertTrue(tree.add(8));
		assertTrue(tree.add(15));
		assertTrue(tree.add(14));
		assertTrue(tree.add(-16));
		assertFalse(tree.add(11));
	}
	
	@Test
	public void testContains() throws Exception {
		tree.add(5);
		tree.add(4);
		tree.add(3);
		tree.add(2);
		tree.add(1);
		tree.add(0);
		tree.add(-1);
		tree.add(-2);
		tree.add(3050);
		assertTrue(tree.contains(5));
		assertTrue(tree.contains(4));
		assertTrue(tree.contains(3));
		assertTrue(tree.contains(2));
		assertTrue(tree.contains(1));
		assertTrue(tree.contains(0));
		assertTrue(tree.contains(-1));
		assertTrue(tree.contains(-2));
		assertTrue(tree.contains(3050));
		assertFalse(tree.contains(-3));
	}
	
	@Test
	public void testDepth() throws Exception {
		assertSame(0, tree.reevaluateDepth());
		tree.add(9);
		assertSame(1, tree.reevaluateDepth());
		tree.add(11);
		assertSame(2, tree.reevaluateDepth());
		tree.add(12);
		assertSame(3, tree.reevaluateDepth());
		tree.add(8);
		tree.add(7);
		assertSame(3, tree.reevaluateDepth());
		tree.add(6);
		assertSame(4, tree.reevaluateDepth());
		tree.add(5);
		assertSame(5, tree.reevaluateDepth());
		tree.add(4);
		tree.add(4);
		assertSame(6, tree.reevaluateDepth());
	}
	
	@Test
	public void testSize() throws Exception {
		assertSame(0, tree.getSize());
		tree.add(3123);
		assertSame(1, tree.getSize());
		tree.add(1);
		tree.add(1);
		assertSame(2, tree.getSize());
	}
	
	@Test
	public void testLeaves() throws Exception {
		assertSame(0, tree.getLeaves());
		tree.add(5);
		assertSame(1, tree.getLeaves());
		tree.add(4);
		assertSame(1, tree.getLeaves());
		tree.add(6);
		assertSame(2, tree.getLeaves());
	}
	
	@Test
	public void largeTest() throws Exception {
		int rnd = random.nextInt(20000)+1;
		int rndSign;
		for(int i = 0; i < 5000; i++) {
			rndSign = random.nextInt(2)+1;
			if(rndSign % 2 == 0)
				tree.add(i*rnd);
			else
				tree.add(-i*rnd);
			assertEquals(tree.getSize(), tree.getSize());
			assertEquals(tree.getDepth(), tree.reevaluateDepth());
		}
		for(int i = 0; i < 5000; i++) {
			assertTrue(tree.contains(i*rnd) || tree.contains(-i*rnd));
		}
	}
	
	@Test
	public void testToString() throws Exception {
		for(int i=0; i<200; i++) {
			tree = new Tree<Integer>();
			int nOne = random.nextInt(99)+300;
			int nTwo = random.nextInt(99)+200;
			int nThree = random.nextInt(99)+100;
			int nFour = random.nextInt(99);
			tree.add(nOne);
			tree.add(nTwo);
			tree.add(nThree);
			tree.add(nFour);
			assertEquals(("(" + nFour + ", " + nThree + ", " + nTwo + ", " + nOne + ")"), tree.toString());
		}
	}
}
