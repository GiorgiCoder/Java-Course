public class Less extends Instruction{
    public Less(){}
    public void execute(Simulator simulator){
        if (simulator.getStack().pop()>simulator.getStack().pop())
            simulator.getStack().push(1);
        else
            simulator.getStack().push(0);
    }
}
