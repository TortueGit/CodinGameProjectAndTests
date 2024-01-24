package com.puzzles.ASCIIArtTheDrunkenBishopAlgorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class GameTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

	@ParameterizedTest
	// @CsvSource({"FC:94,11111100:10010100"})
	@CsvSource({"FC,11111100","94,10010100","51,01010001"})
	void hexConvertString_shouldReturnByteArray(String input, String outputExpected) {
		assertEquals(outputExpected, Game.hexToBin(input).toString());
	}

	@ParameterizedTest
	@MethodSource("provideParameters")
	void testFromCodingame(String input, String outputExpected) {

		provideInput(input);
		Game.main(null);

        assertEquals(outputExpected, outputStreamCaptor.toString().trim());
	}

	static Stream<Arguments> provideParameters() {
		return Stream.of(
				Arguments.of(" ", 
					"""
					+---[CODINGAME]---+
					|                 |
					|                 |
					|                 |
					|                 |
					|        S        |
					|                 |
					|                 |
					|                 |
					|                 |
					+-----------------+"""),
				Arguments.of("51:8e:d2:95:25:73:8c:eb:da:c4:9c:49:e6:0e:a9:d3",
					"""
					+---[CODINGAME]---+
					|          ==o    |
					|       . =o+.    |
					|      . + ..     |
					|       . .+      |
					|        SO o     |
					|        o O      |
					|       o *       |
					|      o E o      |
					|       .         |
					+-----------------+"""),
				Arguments.of("fc:94:b0:c1:e5:b0:98:7c:58:43:99:76:97:ee:9f:b7",
					"""
					+---[CODINGAME]---+
					|       .=o.  .   |
					|     . *+*. o    |
					|      =.*..o     |
					|       o + ..    |
					|        S o.     |
					|         o  .    |
					|          .  . . |
					|              o .|
					|               E.|
					+-----------------+"""),
				Arguments.of("02:64:d6:eb:fd:ca:bc:10:79:92:6e:91:d8:b5:f3:fd",
					"""
					+---[CODINGAME]---+
					|    +.           |
					|   +  .          |
					|    .  ..        |
					|     +.= .       |
					|    ..X.S        |
					|     ..*.o .     |
					|      +  .. .    |
					|     . +  .  .   |
					|        =o    E  |
					+-----------------+"""),
				Arguments.of("a8:6c:78:04:a2:23:00:45:58:f7:1b:bc:dc:11:0a:71",
					"""
					+---[CODINGAME]---+
					|.=+ +.E .        |
					|o  . = . .       |
					|o .   = .        |
					|o. . . * .       |
					|+   . = S        |
					|.. + .           |
					|  . =            |
					|   o             |
					|                 |
					+-----------------+"""),
				Arguments.of("00:00:00:00:00:00:00:00:ff:ff:ff:ff:ff:ff:ff:ff",
					"""
					+---[CODINGAME]---+
					|%....            |
					| .   .           |
					|  .   .          |
					|   .   .         |
					|    .   S        |
					|     .           |
					|      .          |
					|       .         |
					|        ........E|
					+-----------------+"""
			));
		
	}

	void provideInput(String data) {
		ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
		System.setIn(testIn);
	}
}
