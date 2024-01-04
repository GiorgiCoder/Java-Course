public class FJump extends Instruction{
    private int i;
    public FJump(int i){
        this.i = i;
    }
    @Override
    public void execute(Simulator simulator){
        if(simulator.getStack().pop()==0)
            simulator.setProgramCounter(i);
    }
}