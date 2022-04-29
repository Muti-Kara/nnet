package neuralnet.network.layer;

import neuralnet.algebra.matrix.Matrix;

/**
* Fully Connected Layer
* @author Muti Kara
*/
public class FullyConnected extends Layer {
	public final static int RELU = 0;
	public final static int SOFTMAX = 1;
	
	/**
	* Constructor takes three parameters:
	* @param previous layers size
	* @param next layers size
	* @param activation type
	 */
	public FullyConnected(int ... layerDescriptor){
		super(layerDescriptor);
		parameters = new Matrix[2];
		parameters[0] = new Matrix(information[1], information[0]);
		parameters[1] = new Matrix(information[1], 1);
	}
	
	/**
	* 
	* @param input
	* @param softmax
	* @return if softmax is true applies softmax activation otherwise applies ELU activation.
	 */
	@Override
	public Matrix[] forwardPropagation(Matrix[] input){
		switch (information[2]) {
			case RELU:
				return new Matrix[]{ parameters[0].dot(input[0]).sum(parameters[1]).relu() };
			case SOFTMAX:
				return new Matrix[]{ parameters[0].dot(input[0]).sum(parameters[1]).softmax() };
			default:
				return null;
		}
	}

	@Override
	public FullyConnected createClone() {
		FullyConnected fullyConnected = new FullyConnected(information);
		for(int i = 0; i < parameters.length; i++){
			fullyConnected.setParameters(i, parameters[i].createClone());
		}
		return fullyConnected;
	}

}
