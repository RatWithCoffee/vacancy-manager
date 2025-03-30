package vacancy_manager.repos;

import vacancy_manager.rmi_interfaces.CandidateRepo;
import vacancy_manager.rmi_interfaces.ManagerRepo;
import vacancy_manager.rmi_interfaces.VacancyRepo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ReposManager {

    private static VacancyRepo vacancyRepo;
    private static ManagerRepo managerRepo;
    private static CandidateRepo candidateRepo;

    public static void getRegistry() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            vacancyRepo = (VacancyRepo) registry.lookup("VacancyRepo");
            managerRepo = (ManagerRepo) registry.lookup("ManagerRepo");
            candidateRepo = (CandidateRepo) registry.lookup("CandidateRepo");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }

    }


    public static VacancyRepo getVacancyRepo() {
        if (vacancyRepo == null) {
            getRegistry();
        }
        return vacancyRepo;
    }

    public static ManagerRepo getManagerRepo() {
        if (managerRepo == null) {
            getRegistry();
        }
        return managerRepo;
    }

    public static CandidateRepo getCandidateRepo() {
        if (candidateRepo == null) {
            getRegistry();
        }
        return candidateRepo;
    }
}
