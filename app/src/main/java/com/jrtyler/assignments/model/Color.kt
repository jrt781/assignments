package com.jrtyler.assignments.model

enum class Color {
	BLUE {
		override fun toString(): String {
			return "Blue"
		}
	},
	RED {
		override fun toString(): String {
			return "Red"
		}
	},
	GREEN {
		override fun toString(): String {
			return "Green"
		}
	},
	YELLOW {
		override fun toString(): String {
			return "Yellow"
		}
	},
	PINK {
		override fun toString(): String {
			return "Pink"
		}
	},
	ORANGE {
		override fun toString(): String {
			return "Orange"
		}
	},
	PURPLE {
		override fun toString(): String {
			return "Purple"
		}
	},
	TEAL {
		override fun toString(): String {
			return "Teal"
		}
	},
}