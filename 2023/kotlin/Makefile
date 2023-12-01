ifndef DAY
$(error DAY is not set)
endif

day: classes tests resources
	@echo "Created day ${DAY}."

classes:
	@echo "Creating classes."
	sed 's/DAY_NUMBER/${DAY}/g' templates/DayTemplate.kt > src/main/kotlin/Day${DAY}.kt

tests:
	@echo "Creating tests."
	sed 's/DAY_NUMBER/${DAY}/g' templates/DayTestTemplate.kt > src/test/kotlin/Day${DAY}Test.kt

resources:
	@echo "Creating resources."
	touch src/main/resources/examples/Day${DAY}.txt
	touch src/main/resources/input/Day${DAY}.txt
