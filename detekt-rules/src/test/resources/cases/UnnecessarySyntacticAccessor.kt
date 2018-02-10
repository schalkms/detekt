@file:Suppress("unused")

package cases

class UnnecessarySyntheticAccessorPositive {

	private val privateVal = 0

	private val reference = SyntheticAccessorReference()

	private fun privateFun() {}


	internal inner class InternalInnerClass {

		private val x = privateVal
	}

	private inner class PrivateInnerClass {

		private val innerPrivateVal = 0

		fun f() {
			privateVal // reports 1
			privateFun() // reports 1
			UnnecessarySyntheticAccessorPositive.privateStaticFun() // reports 1
			privateStaticFun() // reports 1
			this@UnnecessarySyntheticAccessorPositive.privateVal // reports
		}

		fun ref() {
			reference.referenceTestVal // reports 1
			reference.referenceTestFun() // reports 1
		}

		inner class InnerInnerClass {

			fun f() {
				privateVal // reports 1
				innerPrivateVal // reports 1
			}
		}
	}

	private class NestedClass {

		fun f() {
			UnnecessarySyntheticAccessorPositive.privateStaticFun() // reports 1
		}

		object ObjectClass {

			fun f() {
				privateStaticFun() // reports 1
			}
		}
	}

	companion object {
		private fun privateStaticFun() {}
	}
}
