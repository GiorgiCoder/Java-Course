public class Add extends Instruction {
    public Add(){
    }
    @Override
    public void execute (Simulator simulator){
        simulator.getStack().push(simulator.getStack().pop() + simulator.getStack().pop());
    }
}