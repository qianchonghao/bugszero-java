package com.adaptionsoft.games;

import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 重构的步骤
 * 1. 构建单测
 * 2. 梳理 坏味道： 依赖情结、重复代码、过大类、过长方法
 * 3.
 */
public class GameTest {

	@Test
	public void itsLockedDown() throws Exception {
        // @NOTE: 1. new Random(int seed), 构建random对象。相同的random对象其后续的伪随机序列是相同的。这就是保持单元测试结果和txt文件一致的原因
        Random randomizer = new Random(123455);
        // @NOTE 2. 将标准输出到 resultStream
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));

        // @NOTE 3. 顺序排序 1-15
        IntStream.range(1,15).forEach(i -> GameRunner.playGame(randomizer));

        // @NOTE 4. 对比 标准输出和 GameTest.itsLockedDown.approved.txt的内容是否一致
        Approvals.verify(resultStream.toString());

	}
}
