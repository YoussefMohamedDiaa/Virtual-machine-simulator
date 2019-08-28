

import java.util.Arrays;

public class dataPath {
	static final int instructionMemorySize = 1024;
	static final int dataMemorySize = 1024;
	static final int sizeOfData = 32;
	static final int numberOfRegisters = 15;
	static int PC = 0;
	static boolean[][] instructionMemory = new boolean[instructionMemorySize][18];
	static boolean[][] dataMemory = new boolean[dataMemorySize][sizeOfData];
	static boolean[][] registers = new boolean[numberOfRegisters][sizeOfData];

	public static void main(String[] args) throws Exception {
		instructionMemory[0] = new boolean[] { false, false, true, false, // funct
				false, false, true, true, // rd (offset)
				false, false, true, false, // rs (data reg)
				false, false, false, true, // rt (base)
				false, false // opcode
		};
		registers[2] = toBooleanArray(7);
		registers[1] = toBooleanArray(2);
		System.out.println("Register: " + toInt(registers[2]));
		System.out.println("Memory: " + toInt(dataMemory[5]));
		run1Cycle();
		System.out.println("Register: " + toInt(registers[2]));
		System.out.println("Memory: " + toInt(dataMemory[5]));
//		System.out.println("PC: "+PC);
//		System.out.println("ANS: "+toInt(registers[8]));
	}

	static void run1Cycle() throws Exception {
		boolean[] instruction = instructionMemory[PC];

		boolean[] part0to3 = reverse(Arrays.copyOfRange(instruction, 0, 4));
		boolean[] part4to7 = reverse(Arrays.copyOfRange(instruction, 4, 8));
		boolean[] part8to11 = reverse(Arrays.copyOfRange(instruction, 8, 12));
		boolean[] part12to15 = reverse(Arrays.copyOfRange(instruction, 12, 16));
		boolean[] part16to17 = reverse(Arrays.copyOfRange(instruction, 16, 18));
		boolean[] registerRA = toBooleanArray(8);
		ControlSignals control = Control(part16to17, part0to3);

		boolean[] writeRegister = Mux(part8to11, part4to7, registerRA, control.RegDest);
		boolean[][] localregisters = Registers(part12to15, part8to11, writeRegister, null, control.RegWrite);
		boolean[] readData1 = localregisters[0];
		boolean[] readData2 = localregisters[1];

		boolean[] aluControl = ALU_Control(part0to3, control.ALUOp);

		boolean[] readData2Mux = Mux(readData2, part8to11, part4to7, control.ALUSrc);
		boolean[][] aluResult = ALU(readData1, readData2Mux, aluControl);
		boolean[] actualResult = aluResult[0];
//		System.out.println("actual resutlt: " + toInt(actualResult));
		boolean Zero = aluResult[1][0];

		int PC_Added4 = ADD4(PC);
		boolean[] branch_Result = ADD(part4to7, PC_Added4);
		boolean andedSignal = AND(control.Branch, Zero);
		boolean[] upperMux1 = Mux(toBooleanArray(PC_Added4), branch_Result, andedSignal);
		boolean[] upperMux2 = Mux(upperMux1, readData1, control.Jump);
		PC = toInt(upperMux2);

		boolean[] data = dataMemory(actualResult, readData2, control.MemRead, control.MemWrite);
		boolean[] writeData = Mux(actualResult, data, toBooleanArray(PC_Added4), control.MemToReg);

		Registers(part12to15, part8to11, writeRegister, writeData, control.RegWrite);
	}

	static boolean[][] ALU(boolean[] readData1, boolean[] readData2, boolean[] ALU_Control) throws Exception {
		int control = toInt(ALU_Control);
		int data1 = toInt(readData1);
		int data2 = toInt(readData2);
//		System.out.println("ALU INPUTS: " + data1 + " " + data2);
//		System.out.println("Control: " + control);
		if (control == 0)// ADD
			return new boolean[][] { toBooleanArray(data1 + data2), toBooleanArray(0) };
		else if (control == 1)// SUB
			return new boolean[][] { toBooleanArray(data1 - data2), toBooleanArray(0) };
		else if (control == 2)// MULT
			return new boolean[][] { toBooleanArray(data1 * data2), toBooleanArray(0) };
		else if (control == 3)// DIV
			return new boolean[][] { toBooleanArray(data1 / data2), toBooleanArray(0) };
		else if (control == 4)// MOD
			return new boolean[][] { toBooleanArray(data1 % data2), toBooleanArray(0) };
		else if (control == 5)// COUNT_ONES
			return new boolean[][] { toBooleanArray(Integer.bitCount(data1)), toBooleanArray(0) };// TODO check if it
																									// uses data1 or
																									// data2
		else if (control == 6)// SWITCH_SIGN
			return new boolean[][] { toBooleanArray(-1 * data1), toBooleanArray(0) };// TODO check if it uses data1 or
																						// data2
		else if (control == 7)// POWER
			return new boolean[][] { toBooleanArray((int) Math.pow(data1, data2)), toBooleanArray(0) };
		else if (control == 8)// ABS
			return new boolean[][] { toBooleanArray(Math.abs(data1)), toBooleanArray(0) };// TODO check if it uses data1
																							// or data2
		else if (control == 9)// AND
		{
			boolean[] sol = new boolean[readData1.length];
			for (int i = 0; i < readData1.length; i++)
				sol[i] = readData1[i] & readData2[i];
			return new boolean[][] { sol, toBooleanArray(0) };
		} else if (control == 10)// OR
		{
			boolean[] sol = new boolean[readData1.length];
			for (int i = 0; i < readData1.length; i++)
				sol[i] = readData1[i] | readData2[i];
			return new boolean[][] { sol, toBooleanArray(0) };
		} else if (control == 11)// XOR
		{
			boolean[] sol = new boolean[readData1.length];
			for (int i = 0; i < readData1.length; i++)
				sol[i] = readData1[i] ^ readData2[i];
			return new boolean[][] { sol, toBooleanArray(0) };
		} else if (control == 12)// NOR
		{
			boolean[] sol = new boolean[readData1.length];
			for (int i = 0; i < readData1.length; i++)
				sol[i] = !(readData1[i] | readData2[i]);
			return new boolean[][] { sol, toBooleanArray(0) };
		} else if (control == 13)// XNOR
		{
			boolean[] sol = new boolean[readData1.length];
			for (int i = 0; i < readData1.length; i++)
				sol[i] = !(readData1[i] ^ readData2[i]);
			return new boolean[][] { sol, toBooleanArray(0) };
		} else if (control == 14)// SLL
		{
//			System.out.println("data1 sll: " + data1);
//			System.out.println("data2 sll: " + data2);
			int sll = data1 << data2;
//			boolean[] sol = new boolean[readData1.length];
//			for (int i = 0; i < readData1.length; i++)
//				sol[i] = readData1[(i + data2) % readData1.length];
			return new boolean[][] { toBooleanArray(sll), toBooleanArray(0) };
		} else if (control == 15)// SLR
		{
//			System.out.println("data1 slr: " + data1);
//			System.out.println("data2 slr: " + data2);
			int slr = data1 >> data2;
//			boolean[] sol = new boolean[readData1.length];
//			for (int i = 0; i < readData1.length; i++)
//				sol[i] = readData1[(i - data2) % readData1.length];
			return new boolean[][] { toBooleanArray(slr), toBooleanArray(0) };
		} else if (control == 16)// SLT
			return new boolean[][] { toBooleanArray(data1 < data2 ? 1 : 0), toBooleanArray(0) };
		else if (control == 17)// NOT
		{
			boolean[] sol = new boolean[readData1.length];
			for (int i = 0; i < readData1.length; i++)
				sol[i] = !readData1[i];
			return new boolean[][] { sol, toBooleanArray(0) };
		} else if (control == 18)// JAL
			return new boolean[][] { new boolean[0], toBooleanArray(0) };
		else if (control == 19)// JR
			return new boolean[][] { new boolean[0], toBooleanArray(0) };
		else if (control == 20)// BEQ
			return new boolean[][] { new boolean[0], toBooleanArray(data1 == data2 ? 1 : 0) };
		else if (control == 21)// BNE
			return new boolean[][] { new boolean[0], toBooleanArray(data1 != data2 ? 1 : 0) };
		else if (control == 22)// BRANCH_ON_EVEN
			return new boolean[][] { new boolean[0], toBooleanArray(data1 % 2 == 0 ? 1 : 0) };
		else if (control == 23)// BRANCH_ON_ODD
			return new boolean[][] { new boolean[0], toBooleanArray(data1 % 2 == 1 ? 1 : 0) };
		else if (control == 24)// BRANCH_ON_NEG
			return new boolean[][] { new boolean[0], toBooleanArray(data1 < 0 ? 1 : 0) };
		else if (control == 25)// BRANCH_ON_POS
			return new boolean[][] { new boolean[0], toBooleanArray(data1 > 0 ? 1 : 0) };
		else if (control == 26)// BRANCH_IF_ALL_ONE
		{
			for (int i = 0; i < readData1.length; i++)
				if (!readData1[i])
					return new boolean[][] { new boolean[0], toBooleanArray(0) };
			return new boolean[][] { new boolean[0], toBooleanArray(1) };
		} else if (control == 27)// BRANCH_IF_ALL_ZERO
			return new boolean[][] { new boolean[0], toBooleanArray(data1 == 0 ? 1 : 0) };
		else if (control == 28)// BRANCH_ON_ABSOLUTE
			return new boolean[][] { new boolean[0], toBooleanArray(Math.abs(data1) == Math.abs(data2) ? 1 : 0) };
		else if (control == 29)// BRANCH_ON_DIVISIBLE
			return new boolean[][] { new boolean[0], toBooleanArray(data1 % data2 == 0 ? 1 : 0) };
		else if (control == 30)// LW / SW / STORE_AND_SWAP
			return new boolean[][] { toBooleanArray(data1 + data2), toBooleanArray(0) };

		throw new Exception("Invalid Control Signal");
	}

	static int ADD4(int PC) {
		return PC + 1;
	}

	static boolean[] ADD(boolean[] branchAddress, int PC_Added4) {
		return toBooleanArray(toInt(branchAddress) + PC_Added4);
	}

	static boolean[] signExtent(boolean[] bits) {
		boolean[] extendedBits = new boolean[sizeOfData];
		for (int i = 0; i < sizeOfData; i++)
			extendedBits[i] = i < bits.length ? bits[i] : bits[bits.length - 1];
		return extendedBits;
	}

	static ControlSignals Control(boolean[] opcode, boolean[] funct) throws Exception {
		if (opcode[0] && opcode[1]) // Arithmetic
		{
			boolean[] regdest = { true, false };
			boolean[] aluop = { true, true };
			boolean[] memToReg = { false, false };
			boolean[] aluSrc = { toInt(funct) == 1 ? true : false, false };
			return new ControlSignals(regdest, false, false, false, memToReg, aluop, false, aluSrc, true);
		} else if (!opcode[0] && opcode[1]) // Logical
		{
			boolean[] regdest = { true, false };
			boolean aluop[] = { true, false };
			boolean[] memToReg = { false, false };
			boolean[] aluSrc = { false, false };
			return new ControlSignals(regdest, false, false, false, memToReg, aluop, false, aluSrc, true);
		} else if (opcode[0] && !opcode[1]) // Branches and Jumps
		{
			if (toInt(funct) == 0)// JAL
			{
				boolean[] regdest = { false, true };
				boolean aluop[] = { false, true };
				boolean[] memToReg = { false, true };
				boolean[] aluSrc = { false, false };
				return new ControlSignals(regdest, true, false, false, memToReg, aluop, false, aluSrc, true);// TODO
																												// Review
																												// this
			} else if (toInt(funct) == 1)// JR
			{
				boolean[] regdest = { false, true };
				boolean aluop[] = { false, true };
				boolean[] memToReg = { false, false };
				boolean[] aluSrc = { false, false };
				return new ControlSignals(regdest, true, false, false, memToReg, aluop, false, aluSrc, false);// TODO
																												// Review
																												// this
			} else // Branch
			{
				boolean[] regdest = { false, false };
				boolean aluop[] = { false, true };
				boolean[] memToReg = { false, false };
				boolean[] aluSrc = { false, false };
				return new ControlSignals(regdest, false, true, false, memToReg, aluop, false, aluSrc, false);
			}

		} else if (!opcode[0] && !opcode[1]) {
			if (toInt(funct) == 0) // LoadWord
			{
				boolean[] regdest = { false, false };
				boolean aluop[] = { false, false };
				boolean[] memToReg = { true, false };
				boolean[] aluSrc = { false, true };
				return new ControlSignals(regdest, false, false, true, memToReg, aluop, false, aluSrc, true);
			} else if (toInt(funct) == 1)// StoreWord
			{
				boolean[] regdest = { true, false };
				boolean aluop[] = { false, false };
				boolean[] memToReg = { false, false };
				boolean[] aluSrc = { false, true };
				return new ControlSignals(regdest, false, false, false, memToReg, aluop, true, aluSrc, false);
			} else// LoadAndSwap
			{
				boolean[] regdest = { false, false };
				boolean aluop[] = { false, false };
				boolean[] memToReg = { true, false };
				boolean[] aluSrc = { false, true };
				return new ControlSignals(regdest, false, false, true, memToReg, aluop, true, aluSrc, true);
			}

		}
		throw new Exception("Error in control Invalid Opcode or funct");
	}

	static boolean[] ALU_Control(boolean[] funct, boolean[] ALUOp) {
		if (ALUOp[0] && ALUOp[1]) // Arithmetic
			return toBooleanArray(Math.max(0, toInt(funct) - 1));
		else if (ALUOp[0] && !ALUOp[1])
			return toBooleanArray(toInt(funct) + 9);
		else if (!ALUOp[0] && ALUOp[1]) // Logical Operations
			return toBooleanArray(toInt(funct) + 18);
		else // if(!ALUOp[0] && !ALUOp[1])
			return toBooleanArray(30);
	}

	static boolean[] Mux(boolean[] a, boolean[] b, boolean controlSignal) {
		return controlSignal ? b : a;
	}

	static boolean[] Mux(boolean[] a, boolean[] b, boolean[] c, boolean[] controlSignal) {
		int signal = toInt(controlSignal);
		return signal == 0 ? a : signal == 1 ? b : c;
	}

	static boolean AND(boolean a, boolean b) {
		return a && b;
	}

	static boolean[] dataMemory(boolean[] Address, boolean[] writeData, boolean MemRead, boolean MemWrite) {
		int intAddress = toInt(Address);
		boolean[] oldData = null;
		if (MemRead)
			oldData = dataMemory[intAddress];
		if (MemWrite)
			dataMemory[intAddress] = writeData;
		return oldData;
	}

	static boolean[][] Registers(boolean[] readRegister1, boolean[] readRegister2, boolean[] writeRegister,
			boolean[] writeData, boolean regWrite) {
		/*
		 * returns an Array (length 2) of bits(booleans) position 0 is ReadData1
		 * position 1 is ReadData2
		 */
		boolean[] readData1 = registers[toInt(readRegister1)];
		boolean[] readData2 = registers[toInt(readRegister2)];

		if (regWrite)
			registers[toInt(writeRegister)] = writeData;

		return new boolean[][] { readData1, readData2 };
	}

	// Helpers (Might need them for simpler code but not required)
	static int toInt(boolean[] bits) {
		int sol = 0;
		for (int i = 0; i < bits.length; i++)
			if (bits[i])
				sol |= 1 << i;
		return sol;
	}

	static boolean[] toBooleanArray(int num) {
		boolean[] bools = new boolean[sizeOfData];
		for (int i = 0; i < sizeOfData; i++)
			bools[i] = (num & (1 << i)) != 0;
		return bools;
	}

	static boolean[] reverse(boolean[] in) {
		boolean[] out = new boolean[in.length];
		for (int i = 0; i < in.length; i++)
			out[i] = in[in.length - i - 1];
		return out;
	}
}
