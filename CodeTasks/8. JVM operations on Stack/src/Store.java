public class Store extends Instruction{
    private int i;
    public Store(int i){
        this.i = i;
    }
    @Override
    public void execute(Simulator simulator) {
        simulator.getStack().setValueAtIndex(i,simulator.getStack().pop());
    }
}
