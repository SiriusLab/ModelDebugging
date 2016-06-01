package fr.inria.diverse.trace.gemoc.api;

public interface ITraceNotifier {

	void notifyListeners();

	void addListener(ITraceListener listener);

	void removeListener(ITraceListener listener);

}
