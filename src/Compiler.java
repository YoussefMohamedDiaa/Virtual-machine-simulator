


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Compiler {
	public static HashMap<String, boolean[]> registerMap = new HashMap<>();
	public static ArrayList<boolean[]> commandsList;

//	instructionMemory[0]=new boolean [] {false,false,true,true,//funct 
//			false,false,true,false,//rd
//			false,false,false,true,//rs
//			false,false,false,false,//rt
//			true,true				//opcode
//			};

	public static void parse(String commands) throws Exception {
		registerMap.put("$s0", new boolean[] { false, false, false, false });
		registerMap.put("$s1", new boolean[] { false, false, false, true });
		registerMap.put("$s2", new boolean[] { false, false, true, false });
		registerMap.put("$s3", new boolean[] { false, false, true, true });
		registerMap.put("$s4", new boolean[] { false, true, false, false });
		registerMap.put("$t0", new boolean[] { false, true, false, true });
		registerMap.put("$t1", new boolean[] { false, true, true, false });
		registerMap.put("$t2", new boolean[] { false, true, true, true });
		registerMap.put("$ra", new boolean[] { true, false, false, false });
		registerMap.put("$sp", new boolean[] { true, false, false, true });
		registerMap.put("$v0", new boolean[] { true, false, true, false });
		registerMap.put("$v1", new boolean[] { true, false, true, true });
		registerMap.put("$a0", new boolean[] { true, true, false, false });
		registerMap.put("$a1", new boolean[] { true, true, false, true });
		registerMap.put("$a2", new boolean[] { true, true, true, false });
		
		commandsList = new ArrayList<>();
		// opcode 17-16
		// rt 15-12 command [3]
		// rs 11-8 command[2]
		// rd 7-4 command[1]
		// funct 3-0
		if (commands.length() == 0)
			return;
		String commandArray[] = commands.split("\n");
//			System.out.println(Arrays.toString(commandArray));
		for (int i = 0; i < commandArray.length; i++) {
			String command[] = commandArray[i].split(" ");
			int numberOfVariables = command.length;
			boolean[] rs, rt, rd, booleanCommand;
			rs = rt = rd = new boolean[4];
			booleanCommand = new boolean[18];
			if (numberOfVariables > 3)
				rt = registerMap.get(command[3]);
			if (numberOfVariables > 2)
				rs = registerMap.get(command[2]);
			if (numberOfVariables > 1)
				rd = registerMap.get(command[1]);
			booleanCommand = new boolean[18];
			if (command[0].equals("add")) {
//					System.out.println("add");
				// opcode 11
				// funct 0000
				// rd rs rt
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, true };
				boolean funct[] = new boolean[] { false, false, false, false };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("addi")) {
				// opcode 11
				// funct 0001
				// rd rs immediate
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, true };
				boolean funct[] = new boolean[] { false, false, false, true };
				boolean immediate[] = toBooleanArray(Integer.parseInt(command[2]));
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = immediate[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("sub")) {
				// opcode 11
				// funct 0010
				// rd rs rt
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, true };
				boolean funct[] = new boolean[] { false, false, true, false };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("mul")) {
				// opcode 11
				// funct 0011
				// rd rs rt
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, true };
				boolean funct[] = new boolean[] { false, false, true, true };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("div")) {
				// opcode 11
				// funct 0100
				// rd rs rt
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, true };
				boolean funct[] = new boolean[] { false, true, false, false };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("mod")) {
				// opcode 11
				// funct 0101
				// rd rs rt
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, true };
				boolean funct[] = new boolean[] { false, true, false, true };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("countOne")) {
				// opcode 11
				// funct 0110
				// rd rt
				if (!(numberOfVariables > 2))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, true };
				boolean funct[] = new boolean[] { false, true, true, false };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
//					for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator<= 11; booleanCommandIterator++, j++)
//						booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("switchSign")) {
				// opcode 11
				// funct 0111
				// rd rt
				if (!(numberOfVariables > 2))
					throw new Exception("Enter the proper number of variables.");
				rt = registerMap.get(command[2]);
//				System.out.println("Command 2: " + command[2]);
				boolean opCode[] = new boolean[] { true, true };
				boolean funct[] = new boolean[] { false, true, true, true };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
//					for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator<= 11; booleanCommandIterator++, j++)
//						booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("power")) {
				// opcode 11
				// funct 1000
				// rd rs rt
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, true };
				boolean funct[] = new boolean[] { true, false, false, false };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("absolute")) {
				// opcode 11
				// funct 1001
				// rd rt
				if (!(numberOfVariables > 2))
					throw new Exception("Enter the proper number of variables.");
				rt = registerMap.get(command[2]);
				boolean opCode[] = new boolean[] { true, true };
				boolean funct[] = new boolean[] { true, false, false, true };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
//					for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator<= 11; booleanCommandIterator++, j++)
//						booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("and")) {
				// opcode 10
				// funct 0000
				// rd rs rt
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, false };
				boolean funct[] = new boolean[] { false, false, false, false };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("or")) {
				// opcode 10
				// funct 0001
				// rd rt rs
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, false };
				boolean funct[] = new boolean[] { false, false, false, true };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("xor")) {
				// opcode 10
				// funct 0010
				// rd rt rs
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, false };
				boolean funct[] = new boolean[] { false, false, true, false };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("nor")) {
				// opcode 10
				// funct 0011
				// rd rt rs
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, false };
				boolean funct[] = new boolean[] { false, false, true, true };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];

			} else if (command[0].equals("xnor")) {
				// opcode 10
				// funct 0100
				// rd rt rs
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, false };
				boolean funct[] = new boolean[] { false, true, false, false };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("sll")) {
				// opcode 10
				// funct 0101
				// rd rs immediate
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, false };
				boolean funct[] = new boolean[] { false, true, false, true };
//				boolean immediate[] = toBooleanArray(Integer.parseInt(command[3]));
//				rt = registerMap.get(command[2]);
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("slr")) {
				// opcode 10
				// funct 0110
				// rd rs immediate
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, false };
				boolean funct[] = new boolean[] { false, true, true, false };
//				System.out.println("Command 3 in slr: " + command[3]);
//				boolean immediate[] = toBooleanArray(Integer.parseInt(command[3]));
//				rt = registerMap.get(command[2]);
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("slt")) {
				// opcode 10
				// funct 0111
				// rd rs rt
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, false };
				boolean funct[] = new boolean[] { false, true, true, true };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("not")) {
				// opcode 10
				// funct 1000
				// rd rs undefined
				if (!(numberOfVariables > 2))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { true, false };
				boolean funct[] = new boolean[] { true, false, false, false };
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
//					for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator<= 15; booleanCommandIterator++, j++)
//						booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("jal")) {
				// opcode 01
				// funct 0000
				// rt rs rd
				// dest X X
				if (!(numberOfVariables > 1))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, true };
				boolean funct[] = new boolean[] { false, false, false, false };
				boolean dest[] = registerMap.get(command[1]);
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = dest[j];
//					for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator<= 11; booleanCommandIterator++, j++)
//						booleanCommand[booleanCommandIterator] = rs[j];
//					for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator<= 7; booleanCommandIterator++, j++)
//						booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("jr")) {
				// opcode 01
				// funct 0001
				// rt rs rd
				// dest X X
				if (!(numberOfVariables > 1))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, true };
				boolean funct[] = new boolean[] { false, false, false, true };
				boolean dest[] = registerMap.get(command[1]);
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = dest[j];
//					for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator<= 11; booleanCommandIterator++, j++)
//						booleanCommand[booleanCommandIterator] = rs[j];
//					for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator<= 7; booleanCommandIterator++, j++)
//						booleanCommand[booleanCommandIterator] = rd[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("beq")) {
				// opcode 01
				// funct 0010
				// rt rs offset
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, true };
				boolean funct[] = new boolean[] { false, false, true, false };
				boolean offset[] = toBooleanArray(Integer.parseInt(command[1]));
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = offset[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("bne")) {
				// opcode 01
				// funct 0011
				// rt rs offset
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, true };
				boolean funct[] = new boolean[] { false, false, true, true };
				boolean offset[] = toBooleanArray(Integer.parseInt(command[1]));
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = offset[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("branchOnEven")) {
				// opcode 01
				// funct 0100
				// rt rs offset
				if (!(numberOfVariables > 2))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, true };
				boolean funct[] = new boolean[] { false, true, false, false };
				boolean offset[] = toBooleanArray(Integer.parseInt(command[1]));
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
//				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
//					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = offset[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("branchOnOdd")) {
				// opcode 01
				// funct 0101
				// rt rs offset
				if (!(numberOfVariables > 2))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, true };
				boolean funct[] = new boolean[] { false, true, false, true };
				boolean offset[] = toBooleanArray(Integer.parseInt(command[1]));
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
//				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
//					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = offset[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("branchOnNeq")) {
				// opcode 01
				// funct 0110
				// rt rs offset
				if (!(numberOfVariables > 2))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, true };
				boolean funct[] = new boolean[] { false, true, true, false };
				boolean offset[] = toBooleanArray(Integer.parseInt(command[1]));
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
//				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
//					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = offset[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("branchOnPos")) {
				// opcode 01
				// funct 0111
				// rt rs offset
				if (!(numberOfVariables > 2))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, true };
				boolean funct[] = new boolean[] { false, true, true, true };
				boolean offset[] = toBooleanArray(Integer.parseInt(command[1]));
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
//				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
//					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = offset[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("branchIfAllOne")) {
				// opcode 01
				// funct 1000
				// rt rs offset
				if (!(numberOfVariables > 2))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, true };
				boolean funct[] = new boolean[] { true, false, false, false };
				boolean offset[] = toBooleanArray(Integer.parseInt(command[1]));
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
//				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
//					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = offset[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("branchIfAllZero")) {
				// opcode 01
				// funct 1001
				// rt rs offset
				if (!(numberOfVariables > 2))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, true };
				boolean funct[] = new boolean[] { true, false, false, true };
				boolean offset[] = toBooleanArray(Integer.parseInt(command[1]));
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
//				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
//					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = offset[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("branchOnAbsolute")) {
				// opcode 01
				// funct 1010
				// rt rs offset
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, true };
				boolean funct[] = new boolean[] { true, false, true, false };
				boolean offset[] = toBooleanArray(Integer.parseInt(command[1]));
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = offset[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("branchOnDivisible")) {
				// opcode 01
				// funct 1011
				// rt rs offset
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, true };
				boolean funct[] = new boolean[] { true, false, true, true };
				boolean offset[] = toBooleanArray(Integer.parseInt(command[1]));
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = offset[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("loadWord")) {
				// opcode 00
				// funct 0000
				// rt rs rd
				// base offset rd
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, false };
				boolean funct[] = new boolean[] { false, false, false, false };
				boolean offset[] = toBooleanArray(Integer.parseInt(command[2]));
				rs = registerMap.get(command[1]);
				rt = registerMap.get(command[3]);
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = offset[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("storeWord")) {
				// opcode 00
				// funct 0001
				// rt rs rd
				// base offset rd
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, false };
				boolean funct[] = new boolean[] { false, false, false, true };
				boolean offset[] = toBooleanArray(Integer.parseInt(command[2]));
				rs = registerMap.get(command[1]);
				rt = registerMap.get(command[3]);
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = offset[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			} else if (command[0].equals("storeAndSwap")) {
				// opcode 00
				// funct 0010
				// rt rs rd
				// base offset rd
				if (!(numberOfVariables > 3))
					throw new Exception("Enter the proper number of variables.");
				boolean opCode[] = new boolean[] { false, false };
				boolean funct[] = new boolean[] { false, false, true, false };
				boolean offset[] = toBooleanArray(Integer.parseInt(command[2]));
				rs = registerMap.get(command[1]);
				rt = registerMap.get(command[3]);
				for (int booleanCommandIterator = 16, j = 0; booleanCommandIterator <= 17; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = opCode[j];
				for (int booleanCommandIterator = 12, j = 0; booleanCommandIterator <= 15; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rt[j];
				for (int booleanCommandIterator = 8, j = 0; booleanCommandIterator <= 11; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = rs[j];
				for (int booleanCommandIterator = 4, j = 0; booleanCommandIterator <= 7; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = offset[j];
				for (int booleanCommandIterator = 0, j = 0; booleanCommandIterator <= 3; booleanCommandIterator++, j++)
					booleanCommand[booleanCommandIterator] = funct[j];
			}
			commandsList.add(booleanCommand);
		}
//		commandsList.remove(0);
	}

	static boolean[] toBooleanArray(int num) {
		boolean[] bools = new boolean[4];
		for (int i = 0; i < 4; i++)
			bools[i] = (num & (1 << i)) != 0;
		return dataPath.reverse(bools);
	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String commands = "";
		while (true) {
			String s = sc.nextLine();
			if (s.equals("done")) {
				parse(commands);
				break;
			}
			commands += '\n';
			commands += s;
		}
		for (boolean[] commandArray : commandsList) {
			for (int i = 0; i < commandArray.length; i++)
				System.out.print(commandArray[i] + " ");
			System.out.println();
		}
	}

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream system) {
			br = new BufferedReader(new InputStreamReader(system));
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public String nextLine() throws IOException {
			return br.readLine();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}

		public char nextChar() throws IOException {
			return next().charAt(0);
		}

		public Long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public boolean ready() throws IOException {
			return br.ready();
		}

		public void waitForInput() throws InterruptedException {
			Thread.sleep(4000);
		}
	}
}
