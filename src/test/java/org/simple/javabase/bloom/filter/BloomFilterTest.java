package org.simple.javabase.bloom.filter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
/**
 * 布隆过滤器
 * 用于数据字典和一定容错率的查找场景，是一种节省空间的方法，包括一个位数组和多个独立的hash函数，
 * 插入时：通过K个哈希函数将value映射为k个值g，然后将位数组中已g为index的相应位置1
 * 查找时：通过K个哈希函数得到k个值g',然后判断位数组以'为index的相应位置是否都为1，若全为1，则存在，否则不存在 
 * @author shiya
 *
 */
public class BloomFilterTest {
	private BloomFilter<BigInteger> bloomFilter;
	private Random random;
	private int numBits;
	private List<BigInteger> stored;
	private List<BigInteger> notStored;

	@Before
	public void setUp() {
		numBits = 128;
		random = new Random();
		stored = new ArrayList<BigInteger>();
		notStored = new ArrayList<BigInteger>();
		loadBigIntList(stored, 1000);
		loadBigIntList(notStored, 100);
	}

	@Test
	public void testMayContain() {
		setUpBloomFilter(stored.size());
		int falsePositiveCount = 0;
		for (BigInteger bigInteger : notStored) {
			boolean mightContain = bloomFilter.mightContain(bigInteger);
			boolean isStored = stored.contains(bigInteger);
			if (mightContain && !isStored) {
				falsePositiveCount++;
			}
		}
		System.out.println(falsePositiveCount);
//		assertThat(falsePositiveCount < 5, is(true));
	}

	@Test 
	public void testMayContainGoOverInsertions() {
		setUpBloomFilter(50);
		int falsePositiveCount = 0;
		for (BigInteger bigInteger : notStored) {
			boolean mightContain = bloomFilter.mightContain(bigInteger);
			boolean isStored = stored.contains(bigInteger);
			if (mightContain && !isStored) {
				falsePositiveCount++;
			}
		}
		System.out.println(falsePositiveCount);
//		assertThat(falsePositiveCount, is(notStored.size()));
	}

	private void setUpBloomFilter(int numInsertions) {
		bloomFilter = BloomFilter.create(new BigIntegerFunnel(), numInsertions);
		addStoredBigIntegersToBloomFilter();
	}

	private BigInteger getRandomBigInteger() {
		return new BigInteger(numBits, random);
	}

	private void addStoredBigIntegersToBloomFilter() {
		for (BigInteger bigInteger : stored) {
			bloomFilter.put(bigInteger);
		}
	}

	private void loadBigIntList(List<BigInteger> list, int count) {
		for (int i = 0; i < count; i++) {
			list.add(getRandomBigInteger());
		}
	}

	private class BigIntegerFunnel implements Funnel<BigInteger> {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4885590430913308570L;

		@Override
		public void funnel(BigInteger from, PrimitiveSink into) {
			into.putBytes(from.toByteArray());
		}
	}

}
