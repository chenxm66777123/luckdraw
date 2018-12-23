package com.goldbee.luckdraw.constant.enums;

public class EnumMenus {

	// 按钮等级
	public enum Level {

		one(1, "一级菜单"), two(2, "二级菜单");

		private int key;
		private String val;

		Level() {
		}

		Level(int key, String val) {
			this.key = key;
			this.val = val;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public String getVal() {
			return val;
		}

		public void setVal(String val) {
			this.val = val;
		}
	}

	// 按钮类型 1点击 2 跳转型
	public enum ButtonType {

		click(1, "click点击型按钮"), view(2, "view跳转型按钮");

		private int key;
		private String val;

		ButtonType() {
		}

		ButtonType(int key, String val) {
			this.key = key;
			this.val = val;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public String getVal() {
			return val;
		}

		public void setVal(String val) {
			this.val = val;
		}
	}

}
