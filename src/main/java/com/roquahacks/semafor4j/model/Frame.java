/**
 * 
 * SEMAFOR4J
 * 
 * Copyright (C) 2015 Kristian Kolthoff
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.roquahacks.semafor4j.model;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
/**
 * Representing a <code>Frame</code> of the FrameNet semantic parsing and annotation 
 *<a href="https://github.com/Noahs-ARK/semafor-semantic-parser">project</a>.
 * A <code>Frame</code> consists of its invoking sentence, an identifying <code>name</code>, 
 * the <code>target</code> which is the part of the sentence that actually invoked the <code>
 * Frame</code>, a <code>rank<code> that expresses how many other <code>Frame</code>s are higher
 * weighted than this frame for the sentence and a list of <code>FElement</code>s.
 */
public class Frame implements Comparable<Frame>, Iterable<FElement>{

	private String sentence;
	private String name;
	private String target;
	private int rank;
	private List<FElement> fElements;
	
	public Frame(String sentence, String name, String target, List<FElement> fElements, int rank) {
		this.sentence = sentence;
		this.target = target;
		this.name = name;
		this.rank = rank;
		this.fElements = fElements;
	}
	
	public Frame(String sentence, String name, int rank) {
		this.sentence = sentence;
		this.name = name;
		this.rank = rank;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FElement> getfElements() {
		return fElements;
	}

	public void setfElements(List<FElement> fElements) {
		this.fElements = fElements;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 31 * hash + Objects.hash(this.sentence);
		hash = 31 * hash + Objects.hash(this.name);
		hash = 31 * hash + Objects.hash(this.target);
		hash = 31 * hash + Objects.hash(this.rank);
		hash = 31 * hash + Objects.hash(this.fElements);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		if(!(obj instanceof Frame)) {
			return false;
		}
		Frame that = (Frame) obj;
		if(this.sentence.equals(that.sentence) &&
			   this.name.equals(that.name) &&
			   this.target.equals(that.target) &&
			   this.rank == that.rank) {
			for (int i = 0; i < this.fElements.size(); i++) {
				if(!(this.fElements.get(i).equals(that.fElements.get(i)))) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Compares two <code>Frame</code>s in a way more
	 * less strict mode than the <code>equals</code> method.
	 * For equality, it compares only the frame identifying 
	 * field name.
	 * @param obj the reference object with which to compare
	 * @return <code>true</code>, if the names of the two
	 * <code>Frame</code>s are equal, <code>false</code>
	 * otherwise
	 */
	public boolean equalsLessStrict(Object obj) {
		if(obj == this) {
			return true;
		}
		if(!(obj instanceof Frame)) {
			return false;
		}
		Frame that = (Frame) obj;
		return this.name.equals(that.name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Frame : [" + this.name + ", " + this.target + 
				", " + this.rank + ", " + this.sentence);
		for(FElement felement : this.fElements) {
			sb.append(", " + felement.toString());
		}
		return sb.toString();
	}

	public int compareTo(Frame o) {
		return this.name.compareTo(o.name);
	}

	public Iterator<FElement> iterator() {
		return this.fElements.iterator();
	}
	
}
