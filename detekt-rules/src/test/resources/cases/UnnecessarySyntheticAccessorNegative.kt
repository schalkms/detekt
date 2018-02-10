@file:Suppress("unused")

package cases

class UnnecessarySyntheticAccessorNegative {

	internal val internalVal = 0
	internal fun internalFun() {}

	internal val reference = SyntheticAccessorReference()
	private val referenceTestVal = 0
	private fun referenceTestFun() {}

	private inner class PrivateInnerClass {

		fun f() {
			internalVal
			internalFun()
			internalStaticFun()
			UnnecessarySyntheticAccessorNegative.internalStaticFun()
		}

		fun ref() {
			reference.referenceTestVal
			reference.referenceTestFun()
			SyntheticAccessorReference.referenceStaticTestFun()
		}

		fun redeclaredLocalVal() {
			val referenceTestVal = 0
			print(referenceTestVal)
		}

		fun redeclaredValInLamdba() {
			emptyArray<Int>().forEach { referenceTestVal -> print(referenceTestVal) }
		}
	}

	companion object {
		internal fun internalStaticFun() {}
	}
}

internal class SyntheticAccessorReference {

	internal val referenceTestVal = 0

	internal fun referenceTestFun() {}

	companion object {
		internal fun referenceStaticTestFun() {}
	}
}
