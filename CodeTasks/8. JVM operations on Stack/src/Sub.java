public class Sub extends Instruction {
    public Sub(){

    }
    @Override
    public void execute (Simulator simulator){
        simulator.getStack().push(-(simulator.getStack().pop()) + simulator.getStack().pop());
    }
}