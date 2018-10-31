package com.jrtyler.assignments.model

import java.util.*

object ServerRootModel {

	var courses = TreeSet<Course>()

	fun initTempData() {

		initCS356()
		initSFL110()
		initNDFS100()
		initCS330()

	}
	
	private fun initCS330() {
		val course = Course("CS 330", "Concepts of Programming Languages", "Bryan Morse", Color.TEAL)
		
		courses.add(course)
		
		course.assignments.add(Assignment("Basic Racket", course.id, Date(10, 9, 2018), Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Lists and Recursion", course.id, Date(14, 9, 2018), Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("First-class and higher-order functions", course.id, Date(19, 9, 2018), Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("More Higher-Order Functions", course.id, Date(21, 9, 2018), Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Interpreter 1", course.id, Date(1, 10, 2018), Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Interpreter 2", course.id, Date(12, 10, 2018), Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Interpreter 3", course.id, Date(19, 10, 2018), Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Logic Puzzle 1 - Rosie's Roses", course.id, Date(26, 10, 2018), Time(11, 59, Meridian.PM), "Use the template code to begin this project.", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Logic Puzzle 2 - School's Out!", course.id, Date(26, 10, 2018), Time(11, 59, Meridian.PM), "Use the template code to begin this project."))
		course.assignments.add(Assignment("Sudoku - Prolog 2", course.id, Date(2, 11, 2018), Time(11, 59, Meridian.PM), "Copy the template code from the class website.\nUse recursion to proceed through the table.\nThe TA's are holding a help session for this on 11/1 at 5 PM."))
		course.assignments.add(Assignment("Elixir 1", course.id, Date(9, 11, 2018), Time(11, 59, Meridian.PM)))
		course.assignments.add(Assignment("Elixir 2", course.id, Date(16, 11, 2018), Time(11, 59, Meridian.PM)))
		course.assignments.add(Assignment("Garbage Collection", course.id, Date(21, 11, 2018), Time(11, 59, Meridian.PM)))
		course.assignments.add(Assignment("Lazy Programming", course.id, Date(3, 12, 2018), Time(11, 59, Meridian.PM)))
		course.assignments.add(Assignment("Type Checker", course.id, Date(13, 12, 2018), Time(11, 59, Meridian.PM)))
		course.assignments.add(Assignment("Extra credit: Mid-semester survey", course.id, Date(23, 10, 2018), Time(11, 59, Meridian.PM), "You should get an email about this.\nThis is anonymous!", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Extra credit: End-of-semester survey", course.id, Date(14, 12, 2018), Time(11, 59, Meridian.PM), "You should get an email about this.\nThis is anonymous!"))
		
	}
	
	private fun initCS356() {
		val date1 = Date(13, 9, 2018)
		val date2 = Date(18, 9, 2018)
		val date3 = Date(2, 10, 2018)
		val date4 = Date(16, 10, 2018)
		val date5 = Date(1, 11, 2018)
		val date6 = Date(29, 11, 2018)

		val date7 = Date(11, 9, 2018)
		val date8 = Date(20, 9, 2018)
		val date9 = Date(27, 9, 2018)
		val date10 = Date(2, 10, 2018)
		val date11 = Date(11, 10, 2018)
		val date12 = Date(12, 10, 2018)
		val date13 = Date(23, 10, 2018)
		val date14 = Date(24, 10, 2018)
		val date15 = Date(30, 10, 2018)

		val date16 = Date(8, 11, 2018)
		val date17 = Date(13, 11, 2018)
		val date18 = Date(15, 11, 2018)
		val date19 = Date(29, 11, 2018)
		val date20 = Date(6, 12, 2018)
		val date21 = Date(13, 12, 2018)

		val date22 = Date(2, 11, 2018)

		val course = Course("CS 356", "Designing the User Experience", "Mike Jones", Color.BLUE)

		courses.add(course)

		course.assignments.add(Assignment("Solution Statements", course.id, date1, Time(3, 0, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("UX and the Church", course.id, date2, Time(3, 0, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("App Evaluation", course.id, date2, Time(3, 0, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Full Color Mockup Critiques", course.id, date3, Time(3, 0, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("In class app evaluations", course.id, date4, Time(3, 0, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Demo Day for Mobile", course.id, date5, Time(3, 0, Meridian.PM)))
		course.assignments.add(Assignment("In class evaluations of first prototypes", course.id, date6, Time(3, 0, Meridian.PM)))

		course.assignments.add(Assignment("Problem Statements", course.id, date7, Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Solution Storyboards", course.id, date8, Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Wireframes", course.id, date9, Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Full Color Mockup", course.id, date10, Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("User Study on First Prototype", course.id, date11, Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("First Prototype", course.id, date12, Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("User Study on Second Prototype", course.id, date13, Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Second Prototype", course.id, date14, Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Android Project Final Demo, Demos in class", course.id, date15, Time(11, 59, Meridian.PM), status = AssignmentStatus.DONE))
		
		course.assignments.add(Assignment("Brainstorm ideas to prepare for desktop project", course.id, Date(6, 11, 2018), Time(3, 0, Meridian.PM)))
		course.assignments.add(Assignment("Problem Statements", course.id, date16, Time(3, 0, Meridian.PM)))
		course.assignments.add(Assignment("Contextual Inquiry", course.id, date17, Time(3, 0, Meridian.PM)))
		course.assignments.add(Assignment("User Stories", course.id, date18, Time(3, 0, Meridian.PM)))
		course.assignments.add(Assignment("First Prototype", course.id, date19, Time(3, 0, Meridian.PM)))
		course.assignments.add(Assignment("Second Prototype", course.id, date20, Time(3, 0, Meridian.PM)))
		course.assignments.add(Assignment("Desktop Project Final Turn In", course.id, date21, Time(3, 0, Meridian.PM)))

		val assignment = Assignment("Midterm", course.id, date22, Time(8, 0, Meridian.PM))
		assignment.addTag(Tag("Exam"))
		course.assignments.add(assignment)
	}

	private fun initSFL110() {

		val course = Course("SFL 110", "Food Preparation in the Home", "Dana Adcock", Color.PINK)

		courses.add(course)

		course.assignments.add(Assignment("Quiz - Introduction Video", course.id, Date(18, 9), Time(10, 0, Meridian.PM), "Closed book, no notes.", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Quiz - SFL 110 Learning Suite and Syllabus", course.id, Date(18, 9), Time(10, 0, Meridian.PM), "Closed book, no notes.", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Quiz - Ch. 1, 2, and 20 - Food for Today, Food and Nutrition, and Menu Planning and Meal Preparation", course.id, Date(18, 9), Time(10, 0, Meridian.PM), "Closed book, no notes.", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Quiz - Ch. 3 & 4 - Food Safety and Factors in Food Preparation", course.id, Date(25, 9), Time(10, 0, Meridian.PM), "Closed book, no notes.", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Quiz - Ch. 5 & 6 - Vegetables, Fruits, and Preservation", course.id, Date(2, 10), Time(10, 0, Meridian.PM), "Closed book, no notes.", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Quiz - Ch. 8 - Fats and Oils", course.id, Date(9, 10), Time(10, 0, Meridian.PM), "Closed book, no notes.", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Quiz - Ch. 11 - Proteins: Milk and Cheese", course.id, Date(16, 10), Time(10, 0, Meridian.PM), "Closed book, no notes.", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Quiz - Ch. 13 - Meat, Poultry, and Fish", course.id, Date(23, 10), Time(10, 0, Meridian.PM), "Closed book, no notes.", status = AssignmentStatus.DONE))

		val assignment = Assignment("Midterm", course.id, Date(30, 10), Time(10, 0, Meridian.PM), status = AssignmentStatus.DONE)
		assignment.addTag(Tag("Exam"))
		course.assignments.add(assignment)

		course.assignments.add(Assignment("Quiz - Ch. 12 - Eggs", course.id, Date(30, 10), Time(10, 0, Meridian.PM), "Closed book, no notes.", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Quiz - Ch. 15 & 16 - Grains, Batters, Doughs, and Breads", course.id, Date(6, 11), Time(10, 0, Meridian.PM), "Closed book, no notes."))
		course.assignments.add(Assignment("Quiz - Ch. 17 - Cakes, Cookies, and Pastries", course.id, Date(13, 11), Time(10, 0, Meridian.PM), "Closed book, no notes."))
		course.assignments.add(Assignment("Quiz - Ch. 9 - Sugar", course.id, Date(27, 11), Time(10, 0, Meridian.PM), "Closed book, no notes."))
		course.assignments.add(Assignment("Quiz - Ch. 21 - Meal Service and Hospitality", course.id, Date(4, 12), Time(10, 0, Meridian.PM), "Closed book, no notes."))

		val assignmentFinal = Assignment("Final", course.id, Date(20, 12, 2018), Time(5, 0, Meridian.PM))
		assignmentFinal.addTag(Tag("Exam"))
		course.assignments.add(assignmentFinal)

		course.assignments.add(Assignment("PreAssessment", course.id, Date(12, 9), Time(11, 0, Meridian.AM), "Graded on completion!", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Magnificent Meal Individual Menu, Recipes, & Cost ", course.id, Date(12, 10), Time(12, 0, Meridian.PM), "Use the spreadsheets on the class website.", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Writing Assignment 1", course.id, Date(17, 10), Time(11, 0, Meridian.AM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Writing Assignment 2", course.id, Date(29, 10), Time(11, 0, Meridian.AM)))
		course.assignments.add(Assignment("Magnificent Meal GROUP Market Order, Recipes & Time Management", course.id, Date(9, 11), Time(12, 0, Meridian.PM), "Only one person in the group has to submit.\nSubmit in lab or online."))
		course.assignments.add(Assignment("Home Cooking Assignment ", course.id, Date(28, 11), Time(11, 0, Meridian.AM), "Make sure to take a picture to include in your report!"))
		course.assignments.add(Assignment("Lecture Attendance Form", course.id, Date(5, 12), Time(11, 0, Meridian.AM), "Fill this out each week during the semester.\n Due the last week of the semester."))
		course.assignments.add(Assignment("Magnificent Meal INDIVIDUAL Evaluation", course.id, Date(7, 12), Time(12, 0, Meridian.PM)))

		course.assignments.add(Assignment("Management Lab", course.id, Date(14, 9), Time(12, 0, Meridian.PM), "Be in lab on time!\nRead the management lab recipes.\nRoom JFSB B061 (Down the hall from lecture).", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Meal Planning Lab", course.id, Date(21, 9), Time(12, 0, Meridian.PM), "Be in lab on time!\nRead the meal planning lab recipes.\nRoom JFSB B061 (Down the hall from lecture).", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Safety and Sanitation Lab", course.id, Date(28, 9), Time(12, 0, Meridian.PM), "Be in lab on time!\nRead the safety and sanitation lab recipes.\nRoom JFSB B061 (Down the hall from lecture).", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Fruits and Vegetables Lab", course.id, Date(5, 10), Time(12, 0, Meridian.PM), "Be in lab on time!\nRead the fruits and veggies lab recipes.\nRoom JFSB B061 (Down the hall from lecture).", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Fats and Oils Lab", course.id, Date(12, 10), Time(12, 0, Meridian.PM), "Be in lab on time!\nRead the fats and oils lab recipes.\nRoom JFSB B061 (Down the hall from lecture).", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Milk and Dairy Lab", course.id, Date(19, 10), Time(12, 0, Meridian.PM), "Be in lab on time!\nRead the milk and dairy lab recipes.\nRoom JFSB B061 (Down the hall from lecture).", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Meat, Poultry, Fish and Vegetarianism Lab", course.id, Date(26, 10), Time(12, 0, Meridian.PM), "Be in lab on time!\nRead the meat, poultry, fish, and vegetarianism lab recipes.\nRoom JFSB B061 (Down the hall from lecture).", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Eggs Lab", course.id, Date(2, 11), Time(12, 0, Meridian.PM), "Be in lab on time!\nRead the eggs lab recipes.\nRoom JFSB B061 (Down the hall from lecture)."))
		course.assignments.add(Assignment("Grains and Yeast Breads Lab", course.id, Date(9, 11), Time(12, 0, Meridian.PM), "Be in lab on time!\nRead the grains and yeast breads lab recipes.\nRoom JFSB B061 (Down the hall from lecture)."))
		course.assignments.add(Assignment("Pastry Lab", course.id, Date(16, 11), Time(12, 0, Meridian.PM), "Be in lab on time!\nRead the pastry lab recipes.\nRoom JFSB B061 (Down the hall from lecture)."))
		course.assignments.add(Assignment("Candy Lab", course.id, Date(30, 11), Time(12, 0, Meridian.PM), "Be in lab on time!\nRead the candy lab recipes.\nRoom JFSB B061 (Down the hall from lecture)."))
		course.assignments.add(Assignment("Magnificent Meal Lab", course.id, Date(7, 12), Time(12, 0, Meridian.PM), "Be in lab on time!\nBe ready with your magnificent meal lab recipes!\nRoom JFSB B061 (Down the hall from lecture)."))

	}

	private fun initNDFS100() {

		val course = Course("NDFS 100", "Essentials of Human Nutrition", "Merrill Christensen", Color.RED)

		courses.add(course)

		course.assignments.add(Assignment("Chapter 1 & Appendix C Quiz", course.id, Date(12, 9), Time(1, 35, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Chapter 2 Quiz", course.id, Date(19, 9), Time(11, 30, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Chapters 3-4 Quiz", course.id, Date(26, 9), Time(11, 30, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Chapters 5-6 Quiz", course.id, Date(10, 10), Time(11, 30, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Chapter 7 Quiz", course.id, Date(17, 10), Time(11, 30, Meridian.PM), status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Chapter 8 Quiz", course.id, Date(31, 10), Time(11, 30, Meridian.PM)))
		course.assignments.add(Assignment("Chapters 9-10 Quiz", course.id, Date(7, 11), Time(11, 30, Meridian.PM)))
		course.assignments.add(Assignment("Chapters 11-12 Quiz", course.id, Date(28, 11), Time(11, 30, Meridian.PM)))
		course.assignments.add(Assignment("Chapter 13 Quiz", course.id, Date(5, 12), Time(11, 30, Meridian.PM)))
		course.assignments.add(Assignment("Chapters 14-15 Quiz", course.id, Date(12, 12), Time(11, 30, Meridian.PM)))

		course.assignments.add(Assignment("Does the Media Get It Right", course.id, Date(5, 11), Time(1, 35, Meridian.PM)))
		course.assignments.add(Assignment("Group Consensus Report", course.id, Date(25, 9), Time(1, 35, Meridian.PM), "Only one person in your group has to submit.\nSubmit in class or on Learning Suite.", status = AssignmentStatus.DONE))
		course.assignments.add(Assignment("Plan and Prepare a Meal", course.id, Date(3, 11), Time(9, 0, Meridian.PM), "Make sure to take a picture and include it in your report!"))
		course.assignments.add(Assignment("Dietary Analysis", course.id, Date(8, 11), Time(1, 35, Meridian.PM), "This assignment must be completed over the course of 3 days."))
		course.assignments.add(Assignment("Weight Loss Diets Paper", course.id, Date(29, 11), Time(1, 35, Meridian.PM), "Use at least 3 scholarly sources."))
		course.assignments.add(Assignment("Online Course Evaluation", course.id, Date(13, 12), Time(1, 35, Meridian.PM)))

		val assignmentExam1 = Assignment("Exam 1", course.id, Date(9, 10), Time(10, 0, Meridian.PM), status = AssignmentStatus.DONE)
		assignmentExam1.addTag(Tag("Exam"))
		course.assignments.add(assignmentExam1)

		val assignmentExam2 = Assignment("Exam 2", course.id, Date(30, 10), Time(10, 0, Meridian.PM), status = AssignmentStatus.DONE)
		assignmentExam2.addTag(Tag("Exam"))
		course.assignments.add(assignmentExam2)

		val assignmentExam3 = Assignment("Exam 3", course.id, Date(20, 11), Time(10, 0, Meridian.PM))
		assignmentExam3.addTag(Tag("Exam"))
		course.assignments.add(assignmentExam3)

		val assignmentExam4 = Assignment("Final Exam", course.id, Date(20, 12), Time(10, 0, Meridian.PM))
		assignmentExam4.addTag(Tag("Exam"))
		course.assignments.add(assignmentExam4)
	}

}