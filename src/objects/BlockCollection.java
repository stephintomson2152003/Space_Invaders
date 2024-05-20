package objects;

import java.util.ArrayList;

public class BlockCollection {

	private StaticBlock block1;
	private StaticBlock block2;
	private StaticBlock block3;
	private StaticBlock block4;
	private StaticBlock block5;
	private StaticBlock block6;
	private StaticBlock block7;
	private StaticBlock block8;
	private StaticBlock block9;
	private StaticBlock block10;
	private ArrayList<StaticBlock> collection;

	private Integer xCord;
	private Integer yCord;

	public BlockCollection(Integer xCord, Integer yCord) {
		this.xCord = xCord;
		this.yCord = yCord;
		this.block1 = new StaticBlock(xCord, yCord);
		this.block2 = new StaticBlock(xCord + 20, yCord);
		this.block3 = new StaticBlock(xCord - 20, yCord);

		this.block4 = new StaticBlock(xCord + 20, yCord - 20);
		this.block5 = new StaticBlock(xCord - 20, yCord - 20);
		this.block10 = new StaticBlock(xCord, yCord - 20);

		this.block6 = new StaticBlock(xCord + 20, yCord + 20);
		this.block7 = new StaticBlock(xCord + 20, yCord + 40);
		this.block8 = new StaticBlock(xCord - 20, yCord + 20);
		this.block9 = new StaticBlock(xCord - 20, yCord + 40);
		this.collection = new ArrayList<StaticBlock>();
		setBlock();
	}

	public void setBlock() {
		collection.add(block1);
		collection.add(block2);
		collection.add(block3);
//		collection.add(block4);
//		collection.add(block5);
		collection.add(block6);
		collection.add(block7);
		collection.add(block8);
		collection.add(block9);
		collection.add(block10);
	}

	public ArrayList<StaticBlock> getBlock() {
		return collection;
	}
}
