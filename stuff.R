x = read.table("chemicals.txt", header=TRUE, sep="|")

numAtoms <- function (atom) {
  y = str_extract(x[,4], paste0(atom, "\\d+"))
  y = y[!is.na(y)]
  z = as.integer(substring(y, 1 + nchar(atom)))
  return(z)
  #hist(z,
  #     breaks=0:max(z),
  #     xlab=paste0("Number of ", atom, " atoms"),
  #     main=paste0("Number of chemicals with a given number of ", atom, " atoms"))
  y
}

hist(numAtoms("C"), xlab="Number of C atoms", ylab="Number of molecules", main="Number of molecules with given number of C atoms.")
x = read.table("chemicals.txt", header=TRUE, sep="|")

numAtoms <- function (atom) {
  y = str_extract(x[,4], paste0(atom, "\\d+"))
  y = y[!is.na(y)]
  z = as.integer(substring(y, 1 + nchar(atom)))
  hist(z,
       breaks=0:max(z),
       xlab=paste0("Number of ", atom, " atoms"),
       main=paste0("Number of chemicals with a given number of ", atom, " atoms"))
}

hist(numAtoms("C"), xlab="Number of C atoms", ylab="Number of molecules", main="Number of molecules with given number of C atoms.")


plot(log(x[,5]), x[,6], xlim=c(2,8), xlab="log(mass)", ylab=("logP"), col=heat.colors(z))
