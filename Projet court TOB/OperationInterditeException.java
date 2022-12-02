package allumettes;
public class OperationInterditeException extends RuntimeException {
        /**La levée d'une exception avec un message en paramètre.
        * @param message
        */
		public OperationInterditeException(String message) {
			super(message);
			}
	}
