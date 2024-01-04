public class Load extends Instruction{
    private int i;
    public Load(int i){
        this.i = i;
    }
    @Override
    public void execute(Simulator simulator){
        simulator.getStack().push(simulator.getStack().getValueAtIndex(i));
    }
}