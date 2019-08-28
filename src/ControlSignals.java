

public class ControlSignals {
	boolean[] RegDest;
	boolean Jump;
	boolean Branch;
	boolean MemRead;
	boolean[] MemToReg;
	boolean[] ALUOp;
	boolean MemWrite;
	boolean[] ALUSrc;
	boolean RegWrite;

	ControlSignals() {
		RegDest = new boolean[] {false,false};
		Jump = false;
		Branch = false;
		MemRead = false;
		MemToReg = new boolean[] {false,false};
		ALUOp= new boolean[] {false,false};
		MemWrite = false;
		ALUSrc = new boolean [] {false,false};
		RegWrite = false;
	}

	ControlSignals(boolean[] RegDest, boolean Jump, boolean Branch, boolean MemRead, boolean[] MemToReg, boolean[] ALUOp,
			boolean MemWrite, boolean[] ALUSrc, boolean RegWrite) {
		this.RegDest = new boolean[] {RegDest[0], RegDest[1]};
		this.Jump = Jump;
		this.Branch = Branch;
		this.MemRead = MemRead;
		this.MemToReg = new boolean [] {MemToReg[0], MemToReg[1]};
		this.ALUOp= new boolean [] {ALUOp[0], ALUOp[1]};
		this.MemWrite = MemWrite;
		this.ALUSrc = new boolean [] {ALUSrc[0], ALUSrc[1]};
		this.RegWrite = RegWrite;
	}
}