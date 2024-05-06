package util;

@FunctionalInterface
public interface IAction<T>
{
    public void execute(T arg);
}